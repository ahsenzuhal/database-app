package com.kutuphane.AkilliKutuphane.service;

import com.kutuphane.AkilliKutuphane.Kullanici;
import com.kutuphane.AkilliKutuphane.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Bu sınıfın bir "Service" bileşeni olduğunu belirtir
public class KullaniciService {

    // Gerekli Repository ve Şifreleyiciyi "enjekte ediyoruz" (Autowired)
    // public final KullaniciRepository kullaniciRepository; // ESKİ
    public final KullaniciRepository kullaniciRepository; // YENİ (veya private bırakıp service'e yeni metot ekle)
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public KullaniciService(KullaniciRepository kullaniciRepository, PasswordEncoder passwordEncoder) {
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Yeni bir kullanıcıyı kaydederken şifresini şifreler.
     * @param kullanici Kaydedilecek Kullanici nesnesi (düz metin şifre ile)
     * @return Kaydedilen Kullanici nesnesi (şifrelenmiş şifre ile)
     */
    public Kullanici kullaniciKaydet(Kullanici kullanici) {
        // 1. Kullanıcının düz metin şifresini al
        String duzMetinSifre = kullanici.getSifre();
        
        // 2. Şifreyi BCrypt ile şifrele
        String sifrelenmisSifre = passwordEncoder.encode(duzMetinSifre);
        
        // 3. Kullanıcının şifresini şifrelenmiş olanla güncelle
        kullanici.setSifre(sifrelenmisSifre);
        
        // 4. Veritabanına kaydet
        return kullaniciRepository.save(kullanici);
    }
    
    // (Login, rol kontrolü vb. metotları daha sonra buraya ekleyeceğiz)
}