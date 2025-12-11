package com.kutuphane.AkilliKutuphane;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "yazarlar")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Yazar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yazar_id")
    private Long id;

    @Column(name = "isim")
    private String adSoyad;

    @Column(name = "biyografi")
    private String biyografi;

    // Getter ve Setter'lar
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    @JsonProperty("adSoyad")
    public String getAdSoyad() { 
        return adSoyad; 
    }
    
    public void setAdSoyad(String adSoyad) { 
        this.adSoyad = adSoyad; 
    }

    // Backward compatibility için
    public String getIsim() { 
        return adSoyad; 
    }
    
    public void setIsim(String adSoyad) { 
        this.adSoyad = adSoyad; 
    }

    public String getBiyografi() { 
        return biyografi; 
    }
    
    public void setBiyografi(String biyografi) { 
        this.biyografi = biyografi; 
    }
}