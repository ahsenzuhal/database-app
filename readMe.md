# Akıllı Kütüphane Yönetim Sistemi

Spring Boot backend ve Vanilla JavaScript frontend ile geliştirilmiştir.

<img width="1915" height="948" alt="image" src="https://github.com/user-attachments/assets/e5303276-8323-4e2a-98a6-1440e67e7b46" />


## İçindekiler

- [Özellikler](#özellikler)
- [Teknolojiler](#teknolojiler)
- [Kurulum](#kurulum)
- [Proje Yapısı](#proje-yapısı)
- [API Dokümantasyonu](#api-dokümantasyonu)
- [Frontend Sayfaları](#frontend-sayfaları)
- [Güvenlik](#güvenlik)
- [Veritabanı Şeması](#veritabanı-şeması)
- [Kullanım Kılavuzu](#kullanım-kılavuzu)

##  Özellikler

### Backend Özellikleri
- ✅ JWT tabanlı kimlik doğrulama ve yetkilendirme
- ✅ Rol tabanlı erişim kontrolü (ADMIN/USER)
- ✅ Ödünç alma/iade işlem takibi (OduncIslem Entity)
- ✅ Otomatik ceza hesaplama (gecikme başına günlük 10 TL)
- ✅ E-posta bildirim sistemi (gecikme uyarıları)
- ✅ Global Exception Handling
- ✅ RESTful API tasarımı
- ✅ DTO Pattern kullanımı
- ✅ Service Layer mimarisi

### Frontend Özellikleri
- ✅ Responsive tasarım (Mobil uyumlu)
- ✅ Arama ve filtreleme özellikleri
- ✅ Gerçek zamanlı veri güncellemeleri
- ✅ Kullanıcı dostu arayüz

##  Teknolojiler

### Backend
- **Framework:** Spring Boot 3.5.7
- **Java Version:** Java 21
- **Veritabanı:** MySQL 8.0+
- **Güvenlik:** Spring Security + JWT
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Mail:** JavaMailSender

### Frontend
- **HTML5** - Yapısal markup
- **CSS3** - Modern styling (Dark Academia Theme)
- **Bootstrap 5** - Responsive framework
- **Vanilla JavaScript** - İstemci tarafı mantık
- **Fetch API** - HTTP istekleri
- **Bootstrap Icons** - İkon seti

##  Kurulum

### Gereksinimler
- Java 21 veya üzeri
- Maven 3.6+
- MySQL 8.0+
- Git

### Adım 1: Projeyi Klonlayın
```bash
git clone <repository-url>
cd version3
```

### Adım 2: Veritabanını Oluşturun
MySQL'de veritabanı oluşturun:
```sql
CREATE DATABASE KutuphaneSistemi;
```

### Adım 3: Yapılandırma
`src/main/resources/application.properties` dosyasını düzenleyin:

```properties
# Veritabanı Ayarları
spring.datasource.url=jdbc:mysql://localhost:3306/KutuphaneSistemi
spring.datasource.username=kutuphane_user
spring.datasource.password=your_password

# JWT Ayarları
security.jwt.secret=SuPerGucluUzunBirTokenAnahtari2025!@#ABCxyz123
security.jwt.expiration=3600000

# Mail Ayarları (Opsiyonel)
spring.mail.host=smtp.hotmail.com
spring.mail.port=587
spring.mail.username=your_email@hotmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Adım 4: Projeyi Derleyin ve Çalıştırın
```bash
# Projeyi derle
mvn clean install

# Uygulamayı çalıştır
mvn spring-boot:run
```

Uygulama `http://localhost:9090` adresinde çalışacaktır.

##  Proje Yapısı

```
src/main/java/com/kutuphane/AkilliKutuphane/
├── controller/              # REST API Controller'ları
│   ├── AuthController.java
│   ├── KitapController.java
│   ├── OgrenciController.java
│   ├── YazarController.java
│   ├── KategoriController.java
│   ├── CezaController.java
│   └── OduncIslemController.java
├── service/                 # İş mantığı katmanı
│   ├── KullaniciService.java
│   ├── KitapService.java
│   ├── OgrenciService.java
│   ├── YazarService.java
│   ├── KategoriService.java
│   ├── CezaService.java
│   ├── OduncIslemService.java
│   └── EmailService.java
├── repository/             # Veritabanı erişim katmanı
│   ├── KullaniciRepository.java
│   ├── KitapRepository.java
│   ├── OgrenciRepository.java
│   ├── YazarRepository.java
│   ├── KategoriRepository.java
│   ├── CezaRepository.java
│   └── OduncIslemRepository.java
├── dto/                    # Data Transfer Objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── RegisterRequest.java
│   ├── KitapRequest.java
│   ├── BorrowRequest.java
│   └── OduncIslemResponseDTO.java
├── config/                 # Yapılandırma sınıfları
│   ├── SecurityConfig.java
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   ├── JwtAuthenticationEntryPoint.java
│   └── CustomUserDetailsService.java
├── exception/              # Exception Handler
│   └── GlobalExceptionHandler.java
└── [Entity Classes]        # Veritabanı entity'leri
    ├── Kullanici.java
    ├── Kitap.java
    ├── Ogrenci.java
    ├── Yazar.java
    ├── Kategori.java
    ├── Ceza.java
    └── OduncIslem.java

src/main/resources/
├── static/                 # Frontend dosyaları
│   ├── login.html
│   ├── dashboard.html
│   ├── books.html
│   ├── students.html
│   ├── authors.html
│   ├── categories.html
│   ├── issue-book.html
│   ├── my-books.html
│   ├── borrows.html
│   ├── penalties.html
│   └── global.js
└── application.properties  # Uygulama ayarları
```


## 📝 Notlar

- Proje şu an geliştirme aşamasındadır. Aşağıdaki modüllerde bilinen hatalar mevcuttur ve düzeltilmesi planlanmaktadır:

- Yazar Modülü: Yazarlar sayfası (authors.html) backend ile iletişim kurarken veri eşleşmezliği yaşıyor. Yazar listesi şu an boş veya hatalı görünebiliyor (undefined hatası).

-  Ödünç Alma Sistemi: Backend tarafında altyapısı (Entity/Repository) hazırlanmış olsa da, Frontend bağlantısı (issue-book.html) henüz tamamlanmadı. Kitap atama işlemi şu an çalışmıyor.

- Kategori Gösterimi: Bazı kitap kartlarında kategori isimleri veritabanından çekilirken senkronizasyon sorunu yaşanabiliyor.

### Son Güncellemeler (11.12.2025)

1. **Ödünç İşlem Sistemi**
   - OduncIslem Entity eklendi
   - Detaylı ödünç takibi
   - Gecikme hesaplama ve ceza sistemi

2. **Frontend İyileştirmeleri**
   - Dark Academia teması
   - Responsive tasarım
   - Arama özellikli dropdown'lar
   - Toast bildirimleri

3. **Backend İyileştirmeleri**
   - Global Exception Handler
   - DTO Pattern kullanımı
   - Service Layer mimarisi
   - Tip güvenliği (Long ID'ler)

4. **Güvenlik**
   - JWT Authentication
   - Rol tabanlı yetkilendirme
   - Secure password hashing (BCrypt)
