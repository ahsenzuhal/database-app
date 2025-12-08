package com.kutuphane.AkilliKutuphane.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF korumasını API testi yapacağımız için şimdilik kapatıyoruz
            .csrf(csrf -> csrf.disable())
            // İsteklerin izinlerini ayarlıyoruz
            .authorizeHttpRequests(auth -> auth
                // "/api/" ile başlayan her şeye izin ver (Test için)
                .requestMatchers("/api/**").permitAll()
                // Diğer her şey için giriş yapılması gereksin
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}