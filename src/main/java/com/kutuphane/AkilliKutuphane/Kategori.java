package com.kutuphane.AkilliKutuphane;

import jakarta.persistence.*;

@Entity
@Table(name = "kategoriler")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_id")
    private Integer id;

    @Column(name = "isim", nullable = false, unique = true) // UNIQUE kısıtlaması
    private String isim;

    // --- Constructor'lar ---
    public Kategori() {
    }

    public Kategori(String isim) {
        this.isim = isim;
    }

    // --- Getter ve Setter Metotları ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    @Override
    public String toString() {
        return "Kategori{" +
               "id=" + id +
               ", isim='" + isim + '\'' +
               '}';
    }
}