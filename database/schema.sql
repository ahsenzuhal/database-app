-- `kutuphane` adında bir veritabanı oluştur
CREATE DATABASE IF NOT EXISTS kutuphane;
USE kutuphane;

-- `yazarlar` tablosu
CREATE TABLE yazarlar (
    yazar_id INT AUTO_INCREMENT PRIMARY KEY,
    yazar_ad VARCHAR(255) NOT NULL
);

-- `kategoriler` tablosu
CREATE TABLE kategoriler (
    kategori_id INT AUTO_INCREMENT PRIMARY KEY,
    kategori_ad VARCHAR(255) NOT NULL
);

-- `kitaplar` tablosu
CREATE TABLE kitaplar (
    kitap_id INT AUTO_INCREMENT PRIMARY KEY,
    baslik VARCHAR(255) NOT NULL,
    yazar_id INT,
    kategori_id INT,
    stok_sayisi INT NOT NULL,
    FOREIGN KEY (yazar_id) REFERENCES yazarlar(yazar_id) ON DELETE CASCADE,
    FOREIGN KEY (kategori_id) REFERENCES kategoriler(kategori_id) ON DELETE CASCADE
);

-- `kullanicilar` tablosu
CREATE TABLE kullanicilar (
    kullanici_id INT AUTO_INCREMENT PRIMARY KEY,
    kullanici_ad VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    sifre VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL DEFAULT 'ogrenci' -- 'ogrenci' veya 'admin'
);

-- `odunc_alma_islemleri` tablosu
CREATE TABLE odunc_alma_islemleri (
    odunc_id INT AUTO_INCREMENT PRIMARY KEY,
    kitap_id INT,
    kullanici_id INT,
    odunc_alma_tarihi DATE NOT NULL,
    iade_tarihi DATE,
    FOREIGN KEY (kitap_id) REFERENCES kitaplar(kitap_id) ON DELETE CASCADE,
    FOREIGN KEY (kullanici_id) REFERENCES kullanicilar(kullanici_id) ON DELETE CASCADE
);

-- `cezalar` tablosu
CREATE TABLE cezalar (
    ceza_id INT AUTO_INCREMENT PRIMARY KEY,
    odunc_id INT,
    ceza_miktari DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (odunc_id) REFERENCES odunc_alma_islemleri(odunc_id) ON DELETE CASCADE
);