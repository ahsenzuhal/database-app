package com.kutuphane.AkilliKutuphane.repository;

import com.kutuphane.AkilliKutuphane.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Bu sınıfın bir Repository bileşeni olduğunu Spring'e belirtir
// JpaRepository< Hangi Entity?, Bu Entity'nin ID tipi >
public interface OgrenciRepository extends JpaRepository<Ogrenci, Integer> {
    // save(), findById(), findAll(), delete() gibi metotlar
    // JpaRepository'den otomatik olarak miras alındı!
}