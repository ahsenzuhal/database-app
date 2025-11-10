package com.kutuphane.AkilliKutuphane.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Bu sınıfın bir yapılandırma sınıfı olduğunu belirtir
public class SecurityConfig {

    @Bean // Bu metodu bir "Spring Bean" (nesne) olarak tanımlar
    public PasswordEncoder passwordEncoder() {
        // Sektör standardı olan BCrypt şifreleme algoritmasını kullanıyoruz
        return new BCryptPasswordEncoder();
    }
}