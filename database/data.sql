USE kutuphane;

-- Örnek Yazarlar
INSERT INTO yazarlar (yazar_ad) VALUES ('J.K. Rowling');
INSERT INTO yazarlar (yazar_ad) VALUES ('George Orwell');

-- Örnek Kategoriler
INSERT INTO kategoriler (kategori_ad) VALUES ('Fantastik');
INSERT INTO kategoriler (kategori_ad) VALUES ('Bilim Kurgu');

-- Örnek Kitaplar
INSERT INTO kitaplar (baslik, yazar_id, kategori_id, stok_sayisi) VALUES ('Harry Potter ve Felsefe Taşı', 1, 1, 5);
INSERT INTO kitaplar (baslik, yazar_id, kategori_id, stok_sayisi) VALUES ('1984', 2, 2, 3);

-- Örnek Kullanıcılar
INSERT INTO kullanicilar (kullanici_ad, email, sifre, rol) VALUES ('Ahmet Yılmaz', 'ahmet@example.com', 'sifre123', 'ogrenci');
INSERT INTO kullanicilar (kullanici_ad, email, sifre, rol) VALUES ('Emine Demir', 'emine@example.com', 'sifre456', 'admin');