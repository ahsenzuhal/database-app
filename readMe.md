# Akıllı Kütüphane Yönetim Sistemi (Spring Boot API)

Bu proje, `proje2025-2026.pdf` şartnamesine uygun olarak geliştirilen, REST API tabanlı bir kütüphane otomasyonudur.

*(Not: Bu depo daha önce bir Java Swing prototipi içeriyordu. Bu commit ile proje, PDF şartnamesine uygun olarak Spring Boot katmanlı mimarisine geçirilmiştir.)*

---

## Teknolojiler

* **Backend:** Spring Boot (Java)
* **Veritabanı:** MySQL
* **Mimari:** Katmanlı Mimari (Controller, Service, Repository)
* **Güvenlik:** Spring Security (BCrypt Şifreleme)
* **Veri Erişimi:** Spring Data JPA

---

## Güncel Durum (10 Kasım 2025)

Projenin backend altyapısı ve veritabanı katmanları büyük ölçüde tamamlanmıştır.

### Tamamlanan Adımlar
* **Veritabanı Şeması:** PDF'te istenen `Kitaplar`, `Öğrenciler`, `Yazarlar`, `Kategoriler`, `Kullanıcılar` ve `Cezalar` tabloları için MySQL şeması hazırlandı.
* **Entity (Model) Katmanı:** Tüm veritabanı tabloları için Java (`@Entity`) sınıfları oluşturuldu.
* **Repository Katmanı:** Tüm Entity sınıfları için Spring Data JPA (`@Repository`) arayüzleri oluşturuldu.
* **Güvenlik Altyapısı:** Parola yönetimi için `BCryptPasswordEncoder` entegre edildi.
* **Servis Katmanı (Başlangıç):** `KullaniciService` oluşturuldu.
* **Veri Başlatma (Data Seed):** Uygulama başladığında veritabanında otomatik olarak şifrelenmiş bir `admin` kullanıcısı oluşturan `CommandLineRunner` eklendi.

### Sonraki Adımlar
* Tüm iş mantığı için `Service` katmanının tamamlanması.
* `Controller` katmanının (REST API uç noktaları) yazılması.
* JWT (JSON Web Token) ile kimlik doğrulama sisteminin entegre edilmesi.
* API'leri test etmek için Postman testlerinin yapılması.
* Frontend (Thymeleaf veya HTML/CSS/JS) arayüzünün geliştirilmesi.