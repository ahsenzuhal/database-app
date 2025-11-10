package com.kutuphane.AkilliKutuphane.repository;

import com.kutuphane.AkilliKutuphane.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {

    // Spring Data JPA, metot adından sorgu türetir.
    // Bu metot, "SELECT * FROM kullanicilar WHERE kullanici_adi = ?" sorgusunu
    // bizim için otomatik oluşturur.
    Optional<Kullanici> findByKullaniciAdi(String kullaniciAdi);
}