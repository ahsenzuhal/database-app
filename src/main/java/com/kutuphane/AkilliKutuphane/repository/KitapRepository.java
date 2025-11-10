package com.kutuphane.AkilliKutuphane.repository;

import com.kutuphane.AkilliKutuphane.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Integer> {
}