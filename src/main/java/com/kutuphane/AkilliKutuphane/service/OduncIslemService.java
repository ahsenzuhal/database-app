package com.kutuphane.AkilliKutuphane.service;

import com.kutuphane.AkilliKutuphane.*;
import com.kutuphane.AkilliKutuphane.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

/**
 * Ödünç işlemleri için Service katmanı
 * Tüm ödünç alma ve iade işlemleri burada yönetilir
 */
@Service
public class OduncIslemService {

    private static final int STANDART_IADE_SURESI = 14; // Gün cinsinden
    private static final double GECIKME_CEZA_KATSAYISI = 10.0; // Günlük ceza tutarı (TL)

    private final OduncIslemRepository oduncIslemRepository;
    private final KitapRepository kitapRepository;
    private final OgrenciRepository ogrenciRepository;
    private final CezaService cezaService;
    private final EmailService emailService;

    public OduncIslemService(OduncIslemRepository oduncIslemRepository,
                             KitapRepository kitapRepository,
                             OgrenciRepository ogrenciRepository,
                             CezaService cezaService,
                             EmailService emailService) {
        this.oduncIslemRepository = oduncIslemRepository;
        this.kitapRepository = kitapRepository;
        this.ogrenciRepository = ogrenciRepository;
        this.cezaService = cezaService;
        this.emailService = emailService;
    }

    /**
     * Yeni bir ödünç işlemi oluşturur
     * @param kitapId Ödünç verilecek kitabın ID'si
     * @param ogrenciId Ödünç alan öğrencinin ID'si
     * @return Oluşturulan ödünç işlemi
     */
    @Transactional
    public OduncIslem oduncVer(Long kitapId, Integer ogrenciId) {
        // Kitap kontrolü
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));

        // Kitabın durum kontrolü
        if (!"Rafta".equalsIgnoreCase(kitap.getDurum())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Bu kitap şu anda ödünç verilemez. Durum: " + kitap.getDurum());
        }

        // Öğrenci kontrolü
        Ogrenci ogrenci = ogrenciRepository.findById(ogrenciId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Öğrenci bulunamadı"));

        // Tarih hesaplamaları
        LocalDate bugun = LocalDate.now();
        LocalDate planlananIadeTarihi = bugun.plusDays(STANDART_IADE_SURESI);

        // Ödünç işlemi oluştur
        OduncIslem oduncIslem = new OduncIslem(kitap, ogrenci, bugun, planlananIadeTarihi);
        oduncIslem = oduncIslemRepository.save(oduncIslem);

        // Kitabın durumunu güncelle
        kitap.setDurum("Ödünç Verildi");
        kitap.setOduncAlanOgrenci(ogrenci);
        kitap.setOduncTarihi(bugun);
        kitap.setIadeTarihi(planlananIadeTarihi);
        kitapRepository.save(kitap);

        return oduncIslem;
    }

    /**
     * Kitap iade işlemini gerçekleştirir
     * Gecikme varsa ceza oluşturur ve e-posta gönderir
     * @param kitapId İade edilecek kitabın ID'si
     * @return İade edilen ödünç işlemi
     */
    @Transactional
    public OduncIslem iadeAl(Long kitapId) {
        // Aktif ödünç işlemini bul
        OduncIslem oduncIslem = oduncIslemRepository.findByKitapIdAndDurum(kitapId, "Aktif")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Bu kitap için aktif ödünç işlemi bulunamadı"));

        // Kitap kontrolü
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));

        LocalDate bugun = LocalDate.now();
        Ogrenci ogrenci = oduncIslem.getOgrenci();

        // İade işlemini tamamla
        oduncIslem.setGercekIadeTarihi(bugun);
        oduncIslem.setDurum("İade Edildi");
        oduncIslem = oduncIslemRepository.save(oduncIslem);

        // Gecikme kontrolü ve ceza hesaplama
        if (oduncIslem.isGecikmis()) {
            long gecikmeGunu = oduncIslem.getGecikmeGunu();
            double cezaTutari = gecikmeGunu * GECIKME_CEZA_KATSAYISI;

            // Ceza kaydı oluştur
            cezaService.olustur(ogrenci, kitap, cezaTutari);

            // E-posta bildirimi gönder
            String mesaj = String.format(
                    "Merhaba %s,\n\n%s kitabını %d gün geç iade ettiniz.\nCeza tutarı: %.2f TL\n\nLütfen cezanızı ödeyerek kütüphane hizmetlerinden faydalanmaya devam edebilirsiniz.",
                    ogrenci.getIsim(), kitap.getKitapAdi(), gecikmeGunu, cezaTutari
            );
            emailService.gecIadeBildirimiGonder(
                    ogrenci.getEmail(),
                    "Geç İade Bilgilendirmesi",
                    mesaj
            );
        }

        // Kitabın durumunu güncelle
        kitap.setDurum("Rafta");
        kitap.setOduncAlanOgrenci(null);
        kitap.setOduncTarihi(null);
        kitap.setIadeTarihi(null);
        kitapRepository.save(kitap);

        return oduncIslem;
    }

    /**
     * Belirli bir öğrencinin aktif ödünç işlemlerini getirir
     */
    public List<OduncIslem> ogrencininAktifOduncIslemleri(Integer ogrenciId) {
        return oduncIslemRepository.findByOgrenciIdAndDurum(ogrenciId, "Aktif");
    }

    /**
     * Belirli bir öğrencinin tüm ödünç işlemlerini getirir (geçmiş dahil)
     */
    public List<OduncIslem> ogrencininTumOduncIslemleri(Integer ogrenciId) {
        return oduncIslemRepository.findByOgrenciIdOrderByAlisTarihiDesc(ogrenciId);
    }

    /**
     * Tüm aktif ödünç işlemlerini getirir
     */
    public List<OduncIslem> tumAktifOduncIslemleri() {
        return oduncIslemRepository.findByDurum("Aktif");
    }

    /**
     * Belirli bir ödünç işlemini ID ile getirir
     */
    public OduncIslem getir(Long id) {
        return oduncIslemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ödünç işlemi bulunamadı"));
    }
}

