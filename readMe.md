# Akıllı Kütüphane Yönetim Sistemi (Spring Boot API)

[cite_start]Bu proje, `proje2025-2026.pdf` şartnamesine [cite: 1] [cite_start]uygun olarak geliştirilen, REST API tabanlı [cite: 3, 10] bir kütüphane otomasyonudur.

*(Not: Bu depo daha önce bir Java Swing prototipi içeriyordu. Bu commit ile proje, PDF şartnamesine uygun olarak Spring Boot katmanlı mimarisine geçirilmiştir.)*

---

## Teknolojiler

* [cite_start]**Backend:** Spring Boot (Java) [cite: 2, 9]
* [cite_start]**Veritabanı:** MySQL [cite: 8, 17]
* [cite_start]**Mimari:** Katmanlı Mimari (Controller, Service, Repository) [cite: 13]
* [cite_start]**Güvenlik:** Spring Security (BCrypt Şifreleme) [cite: 14]
* **Veri Erişimi:** Spring Data JPA

---

## Güncel Durum (10 Kasım 2025)

Projenin backend altyapısı ve veritabanı katmanları büyük ölçüde tamamlanmıştır.

### Tamamlanan Adımlar
* [cite_start]**Veritabanı Şeması:** PDF'te [cite: 4] [cite_start]istenen `Kitaplar`, `Öğrenciler`, `Yazarlar`, `Kategoriler`, `Kullanıcılar` ve `Cezalar` [cite: 6] tabloları için MySQL şeması hazırlandı.
* **Entity (Model) Katmanı:** Tüm veritabanı tabloları için Java (`@Entity`) sınıfları oluşturuldu.
* [cite_start]**Repository Katmanı:** Tüm Entity sınıfları için Spring Data JPA (`@Repository`) arayüzleri oluşturuldu[cite: 13].
* [cite_start]**Güvenlik Altyapısı:** Parola yönetimi için `BCryptPasswordEncoder` entegre edildi[cite: 14].
* **Servis Katmanı (Başlangıç):** `KullaniciService` oluşturuldu.
* **Veri Başlatma (Data Seed):** Uygulama başladığında veritabanında otomatik olarak şifrelenmiş bir `admin` kullanıcısı oluşturan `CommandLineRunner` eklendi.

### Sonraki Adımlar
* Tüm iş mantığı için `Service` katmanının tamamlanması.
* [cite_start]`Controller` katmanının (REST API uç noktaları) yazılması[cite: 13].
* [cite_start]JWT (JSON Web Token) ile kimlik doğrulama sisteminin entegre edilmesi[cite: 14].
* [cite_start]API'leri test etmek için Postman testlerinin yapılması[cite: 23].
* [cite_start]Frontend (Thymeleaf veya HTML/CSS/JS) arayüzünün geliştirilmesi[cite: 19, 20].