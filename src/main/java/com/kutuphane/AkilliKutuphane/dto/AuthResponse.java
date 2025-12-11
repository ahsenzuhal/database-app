package com.kutuphane.AkilliKutuphane.dto;

public class AuthResponse {
    private String token;
    private String kullaniciAdi;
    private String rol;

    public AuthResponse(String token, String kullaniciAdi, String rol) {
        this.token = token;
        this.kullaniciAdi = kullaniciAdi;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getRol() {
        return rol;
    }
}



