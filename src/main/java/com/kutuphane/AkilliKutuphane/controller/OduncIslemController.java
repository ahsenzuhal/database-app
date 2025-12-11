package com.kutuphane.AkilliKutuphane.controller;

import com.kutuphane.AkilliKutuphane.OduncIslem;
import com.kutuphane.AkilliKutuphane.dto.BorrowRequest;
import com.kutuphane.AkilliKutuphane.dto.OduncIslemResponseDTO;
import com.kutuphane.AkilliKutuphane.service.OduncIslemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ödünç işlemleri için REST Controller
 */
@RestController
@RequestMapping("/api/odunc-islemler")
@CrossOrigin("*")
public class OduncIslemController {

    private final OduncIslemService oduncIslemService;

    public OduncIslemController(OduncIslemService oduncIslemService) {
        this.oduncIslemService = oduncIslemService;
    }

    /**
     * Yeni ödünç verme işlemi
     * Sadece ADMIN yetkisi gerektirir
     */
    @PostMapping("/odunc-ver")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OduncIslemResponseDTO> oduncVer(@RequestBody BorrowRequest request) {
        OduncIslem oduncIslem = oduncIslemService.oduncVer(
            request.getKitapId(), 
            request.getOgrenciId()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entityToDTO(oduncIslem));
    }

    /**
     * Kitap iade işlemi
     * ADMIN yetkisi gerektirir
     */
    @PostMapping("/{kitapId}/iade")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OduncIslemResponseDTO> iadeAl(@PathVariable Long kitapId) {
        OduncIslem oduncIslem = oduncIslemService.iadeAl(kitapId);
        return ResponseEntity.ok(entityToDTO(oduncIslem));
    }

    /**
     * Tüm aktif ödünç işlemlerini listele
     * ADMIN yetkisi gerektirir
     */
    @GetMapping("/aktif")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OduncIslemResponseDTO>> tumAktifOduncIslemleri() {
        List<OduncIslem> islemler = oduncIslemService.tumAktifOduncIslemleri();
        List<OduncIslemResponseDTO> dtos = islemler.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Belirli bir öğrencinin aktif ödünç işlemlerini getir
     * Öğrenci kendi kayıtlarını görebilir, ADMIN herkesi görebilir
     */
    @GetMapping("/ogrenci/{ogrenciId}")
    public ResponseEntity<List<OduncIslemResponseDTO>> ogrencininOduncIslemleri(
            @PathVariable Integer ogrenciId) {
        List<OduncIslem> islemler = oduncIslemService.ogrencininAktifOduncIslemleri(ogrenciId);
        List<OduncIslemResponseDTO> dtos = islemler.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Belirli bir öğrencinin tüm ödünç işlemlerini getir (geçmiş dahil)
     */
    @GetMapping("/ogrenci/{ogrenciId}/tumu")
    public ResponseEntity<List<OduncIslemResponseDTO>> ogrencininTumOduncIslemleri(
            @PathVariable Integer ogrenciId) {
        List<OduncIslem> islemler = oduncIslemService.ogrencininTumOduncIslemleri(ogrenciId);
        List<OduncIslemResponseDTO> dtos = islemler.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Entity'yi DTO'ya dönüştürür
     */
    private OduncIslemResponseDTO entityToDTO(OduncIslem oduncIslem) {
        OduncIslemResponseDTO dto = new OduncIslemResponseDTO();
        dto.setId(oduncIslem.getId());
        dto.setKitapId(oduncIslem.getKitap().getId());
        dto.setKitapAdi(oduncIslem.getKitap().getKitapAdi());
        dto.setOgrenciId(oduncIslem.getOgrenci().getId());
        dto.setOgrenciAdi(oduncIslem.getOgrenci().getIsim());
        dto.setOgrenciEmail(oduncIslem.getOgrenci().getEmail());
        dto.setAlisTarihi(oduncIslem.getAlisTarihi());
        dto.setPlanlananIadeTarihi(oduncIslem.getPlanlananIadeTarihi());
        dto.setGercekIadeTarihi(oduncIslem.getGercekIadeTarihi());
        dto.setDurum(oduncIslem.getDurum());
        dto.setGecikmis(oduncIslem.isGecikmis());
        dto.setGecikmeGunu(oduncIslem.getGecikmeGunu());
        return dto;
    }
}

