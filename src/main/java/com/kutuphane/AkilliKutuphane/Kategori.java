package com.kutuphane.AkilliKutuphane;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "kategoriler")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_id")
    private Long id;

    // DİKKAT: Frontend burayı 'kategoriAdi' olarak bekliyor.
    // Eğer burada 'ad' veya 'isim' yazıyorsa hata alırsın.
    @Column(name = "kategori_adi", nullable = false)
    private String kategoriAdi;

    // --- GETTER VE SETTER METOTLARI ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }
}