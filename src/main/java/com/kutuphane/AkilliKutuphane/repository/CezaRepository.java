package com.kutuphane.AkilliKutuphane.repository;

import com.kutuphane.AkilliKutuphane.Ceza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CezaRepository extends JpaRepository<Ceza, Integer> {
}