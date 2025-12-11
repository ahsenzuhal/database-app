package com.kutuphane.AkilliKutuphane.service;

import com.kutuphane.AkilliKutuphane.Ceza;
import com.kutuphane.AkilliKutuphane.Kitap;
import com.kutuphane.AkilliKutuphane.Ogrenci;
import com.kutuphane.AkilliKutuphane.repository.CezaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CezaService {

    private final CezaRepository cezaRepository;

    public CezaService(CezaRepository cezaRepository) {
        this.cezaRepository = cezaRepository;
    }

    public List<Ceza> tumCezalar() {
        return cezaRepository.findAll();
    }

    public Ceza olustur(Ogrenci ogrenci, Kitap kitap, Double miktar) {
        Ceza ceza = new Ceza(miktar, "Odenmedi", ogrenci, kitap);
        return cezaRepository.save(ceza);
    }

    public Ceza odemeDurumuGuncelle(Integer id, String durum) {
        return cezaRepository.findById(id)
                .map(ceza -> {
                    ceza.setOdemeDurumu(durum);
                    return cezaRepository.save(ceza);
                })
                .orElse(null);
    }
}



