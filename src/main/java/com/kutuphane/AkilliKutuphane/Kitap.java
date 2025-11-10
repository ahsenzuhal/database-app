package com.kutuphane.AkilliKutuphane;

import jakarta.persistence.*;
import java.time.LocalDate; // Tarih için LocalDate kullanacağız

@Entity
@Table(name = "kitaplar")
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitap_id")
    private Integer id;

    @Column(name = "kitap_adi", nullable = false)
    private String kitapAdi;

    @Column(name = "isbn", unique = true) // ISBN'nin benzersiz olmasını sağlar
    private String isbn;

    @Column(name = "yayin_yili")
    private Integer yayinYili;

    @Column(name = "durum", nullable = false)
    private String durum; // "Rafta", "Ödünç Verildi"

    @Column(name = "odunc_tarihi")
    private LocalDate oduncTarihi;

    @Column(name = "iade_tarihi")
    private LocalDate iadeTarihi;

    // --- İlişkiler ---

    // Bir Kitabın bir Yazarı vardır (ManyToOne: Çok kitap bir yazara ait olabilir)
    @ManyToOne
    @JoinColumn(name = "yazar_id", referencedColumnName = "yazar_id") // Foreign Key
    private Yazar yazar;

    // Bir Kitabın bir Kategorisi vardır (ManyToOne: Çok kitap bir kategoriye ait olabilir)
    @ManyToOne
    @JoinColumn(name = "kategori_id", referencedColumnName = "kategori_id") // Foreign Key
    private Kategori kategori;

    // Bir Kitabı bir öğrenci ödünç alabilir (ManyToOne: Çok kitap bir öğrenciye ödünç verilebilir)
    @ManyToOne
    @JoinColumn(name = "odunc_alan_ogrenci_id", referencedColumnName = "ogrenci_id") // Foreign Key
    private Ogrenci oduncAlanOgrenci;

    // --- Constructor'lar ---
    public Kitap() {
    }

    // Yeni kitap eklerken kullanılabilecek constructor
    public Kitap(String kitapAdi, String isbn, Integer yayinYili, String durum, Yazar yazar, Kategori kategori) {
        this.kitapAdi = kitapAdi;
        this.isbn = isbn;
        this.yayinYili = yayinYili;
        this.durum = durum;
        this.yazar = yazar;
        this.kategori = kategori;
    }

    // --- Getter ve Setter Metotları ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYayinYili() {
        return yayinYili;
    }

    public void setYayinYili(Integer yayinYili) {
        this.yayinYili = yayinYili;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public LocalDate getOduncTarihi() {
        return oduncTarihi;
    }

    public void setOduncTarihi(LocalDate oduncTarihi) {
        this.oduncTarihi = oduncTarihi;
    }

    public LocalDate getIadeTarihi() {
        return iadeTarihi;
    }

    public void setIadeTarihi(LocalDate iadeTarihi) {
        this.iadeTarihi = iadeTarihi;
    }

    public Yazar getYazar() {
        return yazar;
    }

    public void setYazar(Yazar yazar) {
        this.yazar = yazar;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Ogrenci getOduncAlanOgrenci() {
        return oduncAlanOgrenci;
    }

    public void setOduncAlanOgrenci(Ogrenci oduncAlanOgrenci) {
        this.oduncAlanOgrenci = oduncAlanOgrenci;
    }

    @Override
    public String toString() {
        return "Kitap{" +
               "id=" + id +
               ", kitapAdi='" + kitapAdi + '\'' +
               ", isbn='" + isbn + '\'' +
               ", yayinYili=" + yayinYili +
               ", durum='" + durum + '\'' +
               ", oduncTarihi=" + oduncTarihi +
               ", iadeTarihi=" + iadeTarihi +
               '}';
    }
}