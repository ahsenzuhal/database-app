package com.kutuphane.AkilliKutuphane.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Şifreleyici Bean'i (KullaniciService için hala lazım)
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    // Güvenlik Zinciri (Tüm kapıları açıyoruz)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Güvenlik tokenini kapat
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // DİKKAT: Herkese, her yere erişim izni ver!
            )
            .formLogin(login -> login.disable()) // Giriş ekranını kesinlikle kapat
            .httpBasic(basic -> basic.disable()); // Basic auth penceresini kapat

        return http.build();
    }
}