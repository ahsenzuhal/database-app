package com.kutuphane.AkilliKutuphane;

import com.kutuphane.AkilliKutuphane.Kullanici;
import com.kutuphane.AkilliKutuphane.repository.KullaniciRepository;
import com.kutuphane.AkilliKutuphane.service.KullaniciService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AkilliKutuphaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkilliKutuphaneApplication.class, args);
    }


   @Bean
    public CommandLineRunner baslangicAyari(KullaniciRepository kullaniciRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        // Admin kullanıcısını bul
        Kullanici admin = kullaniciRepository.findByKullaniciAdi("admin").orElse(null);

        if (admin != null) {
            // Şifreyi güvenlik standardına göre hash'le ve güncelle
            String hamSifre = "admin123";
            String hashliSifre = passwordEncoder.encode(hamSifre);
            
            admin.setSifre(hashliSifre);
            kullaniciRepository.save(admin);
            
            System.out.println("-- VERİTABANI SIFIRLANDI VE ŞİFRE GÜNCELLENDİ.");
            System.out.println("-- KULLANICI ADI: admin");
            System.out.println("-- ŞİFRE: admin123");
        }
    };
    }

}