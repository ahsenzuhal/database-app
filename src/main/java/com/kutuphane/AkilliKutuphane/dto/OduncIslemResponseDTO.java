package com.kutuphane.AkilliKutuphane.dto;

import java.time.LocalDate;

/**
 * Ödünç işlemi bilgilerini frontend'e döndürmek için DTO
 */
public class OduncIslemResponseDTO {
    private Long id;
    private Long kitapId;
    private String kitapAdi;
    private Integer ogrenciId;
    private String ogrenciAdi;
    private String ogrenciEmail;
    private LocalDate alisTarihi;
    private LocalDate planlananIadeTarihi;
    private LocalDate gercekIadeTarihi;
    private String durum;
    private boolean gecikmis;
    private long gecikmeGunu;

    // Constructor'lar
    public OduncIslemResponseDTO() {
    }

    // Getter ve Setter'lar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKitapId() {
        return kitapId;
    }

    public void setKitapId(Long kitapId) {
        this.kitapId = kitapId;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public Integer getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(Integer ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public String getOgrenciAdi() {
        return ogrenciAdi;
    }

    public void setOgrenciAdi(String ogrenciAdi) {
        this.ogrenciAdi = ogrenciAdi;
    }

    public String getOgrenciEmail() {
        return ogrenciEmail;
    }

    public void setOgrenciEmail(String ogrenciEmail) {
        this.ogrenciEmail = ogrenciEmail;
    }

    public LocalDate getAlisTarihi() {
        return alisTarihi;
    }

    public void setAlisTarihi(LocalDate alisTarihi) {
        this.alisTarihi = alisTarihi;
    }

    public LocalDate getPlanlananIadeTarihi() {
        return planlananIadeTarihi;
    }

    public void setPlanlananIadeTarihi(LocalDate planlananIadeTarihi) {
        this.planlananIadeTarihi = planlananIadeTarihi;
    }

    public LocalDate getGercekIadeTarihi() {
        return gercekIadeTarihi;
    }

    public void setGercekIadeTarihi(LocalDate gercekIadeTarihi) {
        this.gercekIadeTarihi = gercekIadeTarihi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public boolean isGecikmis() {
        return gecikmis;
    }

    public void setGecikmis(boolean gecikmis) {
        this.gecikmis = gecikmis;
    }

    public long getGecikmeGunu() {
        return gecikmeGunu;
    }

    public void setGecikmeGunu(long gecikmeGunu) {
        this.gecikmeGunu = gecikmeGunu;
    }
}


