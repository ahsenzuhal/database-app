package com.kutuphane.AkilliKutuphane;

import jakarta.persistence.*;

@Entity
@Table(name = "yazarlar")
public class Yazar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yazar_id")
    private Integer id;

    @Column(name = "isim", nullable = false)
    private String isim;

    @Column(name = "biyografi", columnDefinition = "TEXT") // TEXT tipini belirtir
    private String biyografi;

    // --- Constructor'lar ---
    public Yazar() {
    }

    public Yazar(String isim, String biyografi) {
        this.isim = isim;
        this.biyografi = biyografi;
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

    public String getBiyografi() {
        return biyografi;
    }

    public void setBiyografi(String biyografi) {
        this.biyografi = biyografi;
    }

    @Override
    public String toString() {
        return "Yazar{" +
               "id=" + id +
               ", isim='" + isim + '\'' +
               ", biyografi='" + biyografi + '\'' +
               '}';
    }
}