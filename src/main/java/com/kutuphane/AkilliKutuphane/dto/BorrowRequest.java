package com.kutuphane.AkilliKutuphane.dto;

/**
 * Ödünç verme işlemi için Request DTO
 */
public class BorrowRequest {
    private Long kitapId;
    private Integer ogrenciId;

    public Long getKitapId() {
        return kitapId;
    }

    public void setKitapId(Long kitapId) {
        this.kitapId = kitapId;
    }

    public Integer getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(Integer ogrenciId) {
        this.ogrenciId = ogrenciId;
    }
}



