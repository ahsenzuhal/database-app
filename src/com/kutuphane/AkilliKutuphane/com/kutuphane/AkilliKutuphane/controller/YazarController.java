package com.kutuphane.AkilliKutuphane.controller;

import com.kutuphane.AkilliKutuphane.Yazar;
import com.kutuphane.AkilliKutuphane.service.YazarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Bu sınıfın bir REST API olduğunu belirtir
@RequestMapping("/api/yazarlar") // Bu sınıftaki tüm adresler "/api/yazarlar" ile başlar
public class YazarController {

    private final YazarService yazarService;

    @Autowired
    public YazarController(YazarService yazarService) {
        this.yazarService = yazarService;
    }

    // GET: Tüm yazarları listele
    @GetMapping
    public List<Yazar> tumYazarlar() {
        return yazarService.tumYazarlariGetir();
    }

    // POST: Yeni yazar ekle
    @PostMapping
    public ResponseEntity<Yazar> yazarEkle(@RequestBody Yazar yazar) {
        Yazar yeniYazar = yazarService.yazarKaydet(yazar);
        return new ResponseEntity<>(yeniYazar, HttpStatus.CREATED);
    }

    // PUT: Yazar güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Yazar> yazarGuncelle(@PathVariable Long id, @RequestBody Yazar yazarBilgileri) {
        Yazar mevcutYazar = yazarService.yazarGetir(id);
        if (mevcutYazar != null) {
            mevcutYazar.setIsim(yazarBilgileri.getIsim());
            mevcutYazar.setBiyografi(yazarBilgileri.getBiyografi());
            Yazar guncellenmisYazar = yazarService.yazarKaydet(mevcutYazar);
            return new ResponseEntity<>(guncellenmisYazar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Yazar sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> yazarSil(@PathVariable Long id) {
        yazarService.yazarSil(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}