package com.kutuphane.AkilliKutuphane.service;

import com.kutuphane.AkilliKutuphane.Ceza;
import com.kutuphane.AkilliKutuphane.Kitap;
import com.kutuphane.AkilliKutuphane.OduncIslem;
import com.kutuphane.AkilliKutuphane.Ogrenci;
import com.kutuphane.AkilliKutuphane.repository.CezaRepository;
import com.kutuphane.AkilliKutuphane.repository.KitapRepository;
import com.kutuphane.AkilliKutuphane.repository.OduncIslemRepository;
import com.kutuphane.AkilliKutuphane.repository.OgrenciRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OduncIslemService {

    private static final int STANDART_IADE_SURESI = 14; 
    private static final double GECIKME_CEZA_KATSAYISI = 5.0; // Günlük 5 TL

    private final OduncIslemRepository oduncIslemRepository;
    private final KitapRepository kitapRepository;
    private final OgrenciRepository ogrenciRepository;
    private final CezaRepository cezaRepository; // Cezayı garanti kaydetmek için ekledik
    // private final EmailService emailService; // Email servisini şimdilik kapattım, hata almamak için

    public OduncIslemService(OduncIslemRepository oduncIslemRepository,
                             KitapRepository kitapRepository,
                             OgrenciRepository ogrenciRepository,
                             CezaRepository cezaRepository) {
        this.oduncIslemRepository = oduncIslemRepository;
        this.kitapRepository = kitapRepository;
        this.ogrenciRepository = ogrenciRepository;
        this.cezaRepository = cezaRepository;
    }

    /**
     * KİTAP ÖDÜNÇ VERME
     */
    @Transactional
    public void oduncVer(Long kitapId, Integer ogrenciId) {
        // 1. Kitap ve Öğrenci Kontrolü
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı ID: " + kitapId));

        if (!"Rafta".equalsIgnoreCase(kitap.getDurum())) {
            throw new RuntimeException("Bu kitap şu an müsait değil! Durumu: " + kitap.getDurum());
        }

        Ogrenci ogrenci = ogrenciRepository.findById(ogrenciId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı ID: " + ogrenciId));

        // 2. Tarihleri Ayarla
        LocalDate bugun = LocalDate.now();
        LocalDate planlananTarih = bugun.plusDays(STANDART_IADE_SURESI);

        // 3. Kitabı Güncelle
        kitap.setDurum("Ödünç Verildi");
        kitap.setOduncAlanOgrenci(ogrenci);
        kitap.setOduncTarihi(bugun);
        kitap.setIadeTarihi(planlananTarih);
        kitapRepository.save(kitap);

        // 4. İşlem Kaydı Oluştur
        OduncIslem islem = new OduncIslem();
        islem.setKitap(kitap);
        islem.setOgrenci(ogrenci);
        islem.setAlisTarihi(bugun);
        islem.setPlanlananIadeTarihi(planlananTarih);
        islem.setDurum("Aktif"); // <--- SQL HATASINI ÇÖZEN SATIR
        
        oduncIslemRepository.save(islem);
        
        System.out.println("✅ Kitap verildi: " + kitap.getKitapAdi());
    }

    /**
     * KİTAP İADE ALMA VE CEZA HESAPLAMA
     */
    @Transactional
    public void iadeAl(Long kitapId) {
        // 1. Kitabı Bul ve Rafa Kaldır
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        
        kitap.setDurum("Rafta");
        kitap.setOduncAlanOgrenci(null);
        kitap.setOduncTarihi(null);
        kitap.setIadeTarihi(null);
        kitapRepository.save(kitap);

        // 2. Aktif İşlem Kaydını Bul
        // Burada repository metodunu manuel filtreliyoruz ki hata çıkmasın
        OduncIslem islem = oduncIslemRepository.findAll().stream()
                .filter(k -> k.getKitap().getId().equals(kitapId) && k.getGercekIadeTarihi() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Bu kitap için aktif ödünç kaydı bulunamadı!"));

        // 3. İşlemi Kapat
        islem.setGercekIadeTarihi(LocalDate.now());
        islem.setDurum("İade Edildi");
        oduncIslemRepository.save(islem);

        // 4. CEZA HESAPLAMA (GARANTİ YÖNTEM)
        long gecikmeGun = ChronoUnit.DAYS.between(islem.getPlanlananIadeTarihi(), LocalDate.now());

        if (gecikmeGun > 0) {
            double cezaTutari = gecikmeGun * GECIKME_CEZA_KATSAYISI;
            
            Ceza ceza = new Ceza();
            ceza.setOgrenci(islem.getOgrenci());
            ceza.setKitap(kitap);
            ceza.setCezaMiktari(cezaTutari);
            ceza.setOdemeDurumu("Ödenmedi");
            
            cezaRepository.save(ceza); // Cezayı veritabanına çakıyoruz
            
            System.out.println("⚠️ GECİKME CEZASI KESİLDİ: " + cezaTutari + " TL");
            
            // Mail gönderme kodu buraya eklenebilir (try-catch içinde)
        }
    }

    // --- YARDIMCI METODLAR (Frontend İçin) ---

    public List<OduncIslem> tumAktifOduncIslemleri() {
        // Sadece 'Aktif' olanları veya iade tarihi null olanları döndür
        return oduncIslemRepository.findAll().stream()
                .filter(islem -> islem.getGercekIadeTarihi() == null)
                .toList();
    }
}