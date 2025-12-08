package com.kutuphane.AkilliKutuphane;

import com.kutuphane.AkilliKutuphane.Kullanici;
import com.kutuphane.AkilliKutuphane.service.KullaniciService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AkilliKutuphaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkilliKutuphaneApplication.class, args);
    }

    // === YENİ EKLENEN KISIM BAŞLANGIÇ ===
    /**
     * Uygulama ilk başladığında çalışacak kod bloğu.
     * Veritabanında bir admin kullanıcısı yoksa, oluşturur.
     */
    @Bean
    public CommandLineRunner initialAdminUser(KullaniciService kullaniciService) {
        return args -> {
            // Admin kullanıcısını veritabanında ara
            var adminUser = kullaniciService.kullaniciRepository.findByKullaniciAdi("admin");
            
            // Eğer "admin" adında bir kullanıcı YÖKSA, oluştur
            if (adminUser.isEmpty()) {
                System.out.println("Admin kullanıcısı bulunamadı, oluşturuluyor...");
                Kullanici admin = new Kullanici();
                admin.setKullaniciAdi("admin");
                admin.setSifre("admin123"); // Şifreyi düz metin olarak veriyoruz
                admin.setRol("ADMIN");
                
                // Servis, bu şifreyi otomatik şifreleyip kaydedecek
                kullaniciService.kullaniciKaydet(admin); 
                System.out.println("Admin kullanıcısı başarıyla oluşturuldu.");
            } else {
                System.out.println("Admin kullanıcısı zaten mevcut.");
            }
        };
    }
    // === YENİ EKLENEN KISIM BİTİŞ ===
}