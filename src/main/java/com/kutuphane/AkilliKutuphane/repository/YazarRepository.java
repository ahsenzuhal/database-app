package com.kutuphane.AkilliKutuphane.repository;

import com.kutuphane.AkilliKutuphane.Yazar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YazarRepository extends JpaRepository<Yazar, Integer> {
}