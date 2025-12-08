# Akıllı Kütüphane Yönetim Sistemi

Spring Boot tabanlı REST API kütüphane otomasyon sistemi.

## Teknolojiler

- **Backend:** Spring Boot 3.5.7 (Java 21)
- **Veritabanı:** MySQL
- **Mimari:** Katmanlı Mimari (Controller, Service, Repository)
- **Güvenlik:** Spring Security (BCrypt Şifreleme)
- **Veri Erişimi:** Spring Data JPA
- **Build Tool:** Maven

## Gereksinimler

- Java 21 veya üzeri
- Maven 3.6+
- MySQL 8.0+
- Git

##  Kurulum

### 1. Projeyi Klonlayın
```bash
git clone <repository-url>
cd AkilliKutuphane
```

### 2. Veritabanını Hazırlayın
MySQL'de `KutuphaneSistemi` adında bir veritabanı oluşturun:
```sql
CREATE DATABASE KutuphaneSistemi;
```

### 3. Veritabanı Ayarlarını Yapılandırın
`src/main/resources/application.properties` dosyasındaki veritabanı bilgilerini kendi ayarlarınıza göre güncelleyin:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/KutuphaneSistemi
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Projeyi Derleyin ve Çalıştırın
```bash
# Projeyi derle
mvn clean install

# Uygulamayı çalıştır
mvn spring-boot:run
```

Uygulama `http://localhost:9090` adresinde çalışacaktır.

## 📁 Proje Yapısı

```
src/main/java/com/kutuphane/AkilliKutuphane/
├── controller/          # REST API Controller'ları
│   └── YazarController.java
├── service/            # İş mantığı katmanı
│   ├── KullaniciService.java
│   └── YazarService.java
├── repository/         # Veritabanı erişim katmanı
│   ├── CezaRepository.java
│   ├── KategoriRepository.java
│   ├── KitapRepository.java
│   ├── KullaniciRepository.java
│   ├── OgrenciRepository.java
│   └── YazarRepository.java
├── config/            # Yapılandırma sınıfları
│   └── SecurityConfig.java
└── [Entity Classes]   # Veritabanı entity'leri
    ├── Ceza.java
    ├── Kategori.java
    ├── Kitap.java
    ├── Kullanici.java
    ├── Ogrenci.java
    └── Yazar.java
```

## 🔌 API Endpoint'leri

### Base URL
```
http://localhost:9090
```

### Yazarlar API

#### Tüm Yazarları Listele
```http
GET /api/yazarlar
```

**Response:**
```json
[
  {
    "id": 1,
    "isim": "Ahmet Ümit",
    "biyografi": "Türk yazar ve şair..."
  }
]
```

#### Yeni Yazar Ekle
```http
POST /api/yazarlar
Content-Type: application/json

{
  "isim": "Yazar Adı",
  "biyografi": "Yazar biyografisi"
}
```

#### Yazar Güncelle
```http
PUT /api/yazarlar/{id}
Content-Type: application/json

{
  "isim": "Güncellenmiş Ad",
  "biyografi": "Güncellenmiş biyografi"
}
```

#### Yazar Sil
```http
DELETE /api/yazarlar/{id}
```

##  Güvenlik

- Uygulama başladığında otomatik olarak bir admin kullanıcısı oluşturulur:
  - **Kullanıcı Adı:** `admin`
  - **Şifre:** `admin123`
  - **Rol:** `ADMIN`

- Şifreler BCrypt ile şifrelenerek veritabanında saklanır.

- Şu anda `/api/**` endpoint'leri herkese açıktır (test amaçlı).

## 📊 Veritabanı Şeması

Proje aşağıdaki tabloları içerir:
- `kullanicilar` - Sistem kullanıcıları
- `yazarlar` - Kitap yazarları
- `kitaplar` - Kütüphane kitapları
- `ogrenciler` - Öğrenci bilgileri
- `kategoriler` - Kitap kategorileri
- `cezalar` - Öğrenci cezaları

Tablolar JPA tarafından otomatik olarak oluşturulur/güncellenir (`spring.jpa.hibernate.ddl-auto=update`).

##  Test Etme

### Postman ile Test
1. Postman'i açın
2. Yeni bir request oluşturun
3. Method olarak `GET`, `POST`, `PUT` veya `DELETE` seçin
4. URL olarak `http://localhost:9090/api/yazarlar` girin
5. POST ve PUT için Body sekmesinde JSON formatında veri gönderin

### cURL ile Test
```bash
# Tüm yazarları listele
curl http://localhost:9090/api/yazarlar

# Yeni yazar ekle
curl -X POST http://localhost:9090/api/yazarlar \
  -H "Content-Type: application/json" \
  -d '{"isim":"Test Yazar","biyografi":"Test biyografi"}'
```

##  Geliştirme Notları

- Proje Spring Boot 3.5.7 ve Java 21 kullanmaktadır
- Veritabanı bağlantı ayarları `application.properties` dosyasındadır
- SQL sorguları konsolda gösterilir (`spring.jpa.show-sql=true`)
- Tablolar otomatik olarak oluşturulur/güncellenir

##  Sonraki Adımlar

- [ ] Diğer entity'ler için Controller'ların oluşturulması
- [ ] JWT (JSON Web Token) kimlik doğrulama entegrasyonu
- [ ] API dokümantasyonu (Swagger/OpenAPI)
- [ ] Unit testlerin yazılması
- [ ] Frontend arayüzünün geliştirilmesi

##  Geliştirici

Proje geliştirme aşamasındadır.
