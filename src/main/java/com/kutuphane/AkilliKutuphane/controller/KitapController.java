package com.kutuphane.AkilliKutuphane.controller;

import com.kutuphane.AkilliKutuphane.Kitap; 
import com.kutuphane.AkilliKutuphane.dto.BorrowRequest;
import com.kutuphane.AkilliKutuphane.dto.KitapRequest;
import com.kutuphane.AkilliKutuphane.service.KitapService;
import com.kutuphane.AkilliKutuphane.service.OduncIslemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kitap yönetimi için REST Controller
 * Ödünç alma/iade işlemleri artık OduncIslemController üzerinden yapılmalı
 */
@RestController
@RequestMapping("/api/kitaplar")
@CrossOrigin(origins = "*")
public class KitapController {

    private final KitapService kitapService;
    private final OduncIslemService oduncIslemService;

    public KitapController(KitapService kitapService, OduncIslemService oduncIslemService) {
        this.kitapService = kitapService;
        this.oduncIslemService = oduncIslemService;
    }

    // --- GET: Listeleme ---
    @GetMapping
    public List<Kitap> liste(@RequestParam(value = "q", required = false) String arama) {
        return kitapService.tumKitaplar(arama);
    }

    // --- POST: Ekleme (MEVCUT SAĞLAM METHOD) ---
    // Frontend buraya istek atacak
    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')") // Şimdilik test için kapalı kalsın, hata alırsan açarız
    public ResponseEntity<Kitap> ekle(@RequestBody KitapRequest request) {
        Kitap kitap = new Kitap();
        kitap.setKitapAdi(request.getKitapAdi());
        kitap.setIsbn(request.getIsbn());
        kitap.setYayinYili(request.getYayinYili());
        //kitap.setDurum(request.getDurum()); // Durum bilgisini de ekleyelim

        // Service üzerinden kayıt işlemi (Repository'yi Service kullanır)
        Kitap kaydedilen = kitapService.kitapEkle(kitap, request.getYazarId(), request.getKategoriId());
        
        return new ResponseEntity<>(kaydedilen, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Kitap guncelle(@PathVariable Long id, @RequestBody KitapRequest request) {
        Kitap guncel = new Kitap();
        guncel.setKitapAdi(request.getKitapAdi());
        guncel.setIsbn(request.getIsbn());
        guncel.setYayinYili(request.getYayinYili());
        return kitapService.kitapGuncelle(id, guncel, request.getYazarId(), request.getKategoriId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> sil(@PathVariable Long id) {
        kitapService.kitapSil(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ödünç verme işlemi (Yeni sistem - OduncIslem kullanır)
     * Geriye uyumluluk için korundu, yeni sistem OduncIslemController'ı kullanmalı
     * @deprecated Yeni endpoint: POST /api/odunc-islemler/odunc-ver kullanılmalı
     */
    @Deprecated
    @PostMapping("/{id}/odunc")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Kitap> oduncAl(@PathVariable Long id, @RequestBody BorrowRequest request) {
        // Yeni sistem kullanılıyor
        oduncIslemService.oduncVer(id, request.getOgrenciId());
        // Kitap bilgisini geri döndür
        List<Kitap> kitaplar = kitapService.tumKitaplar(null);
        Kitap kitap = kitaplar.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        return ResponseEntity.ok(kitap);
    }

    /**
     * İade işlemi (Yeni sistem - OduncIslem kullanır)
     * Geriye uyumluluk için korundu, yeni sistem OduncIslemController'ı kullanmalı
     * @deprecated Yeni endpoint: POST /api/odunc-islemler/{kitapId}/iade kullanılmalı
     */
    @Deprecated
    @PostMapping("/{id}/iade")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Kitap> iadeEt(@PathVariable Long id) {
        // Yeni sistem kullanılıyor
        oduncIslemService.iadeAl(id);
        // Kitap bilgisini geri döndür
        List<Kitap> kitaplar = kitapService.tumKitaplar(null);
        Kitap kitap = kitaplar.stream()
                .filter(k -> k.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        return ResponseEntity.ok(kitap);
    }
}

