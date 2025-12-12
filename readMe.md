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

## 📂 Güncel Dosya Yapısı (Project Structure)

Projenin backend ve frontend mimarisi aşağıdaki gibidir:

src/
├── main/
│   ├── java/com/kutuphane/AkilliKutuphane/
│   │   ├── config/             # Security ve JWT yapılandırmaları
│   │   ├── controller/         # REST API uç noktaları (Kitap, Yazar, Ödünç vb.)
│   │   ├── dto/                # Veri transfer objeleri (BorrowRequest, KitapRequest vb.)
│   │   ├── exception/          # Global hata yönetimi
│   │   ├── model/              # Veritabanı varlıkları (Entity)
│   │   ├── repository/         # Veritabanı erişim katmanı (JPA)
│   │   └── service/            # İş mantığı (Business Logic)
│   └── resources/
│       ├── static/             # Frontend Dosyaları
│       │   ├── authors.html    # Yazar yönetimi
│       │   ├── books.html      # Kitap yönetimi (Mavi/Beyaz Tema)
│       │   ├── borrows.html    # Ödünç takibi ve iade
│       │   ├── dashboard.html  # Yönetim paneli
│       │   ├── login.html      # Giriş ekranı
│       │   ├── penalties.html  # Ceza yönetimi
│       │   └── ...
│       └── application.properties


## 📝 Notlar

- Proje şu an geliştirme aşamasındadır. Aşağıdaki modüllerde bilinen hatalar mevcuttur ve düzeltilmesi planlanmaktadır:

- Kullanıcı Kaydı (Register): Şu an sisteme sadece veritabanından eklenen kullanıcılar girebiliyor. "Kayıt Ol" ekranı ve backend bağlantısı henüz yapılmadı.

- Öğrenci Paneli (User UI): Standart kullanıcı (Öğrenci) giriş yaptığında sadece kendi ödünç aldığı kitapları görebileceği "Kitaplarım" sayfası henüz aktif değil.

-  E-Posta Bildirimleri: Kitap iade tarihi yaklaştığında veya geciktiğinde otomatik e-posta gönderen (JavaMailSender) mekanizma henüz entegre edilmedi.


4. **Güvenlik**
   - JWT Authentication
   - Rol tabanlı yetkilendirme
   - Secure password hashing (BCrypt)
>>>>>>> 894054f2ec386db839c72290567606e2e9c5f809
