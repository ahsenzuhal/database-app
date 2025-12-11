package com.kutuphane.AkilliKutuphane.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void gecIadeBildirimiGonder(String aliciEmail, String konu, String mesaj) {
        if (!StringUtils.hasText(aliciEmail)) {
            log.warn("E-posta adresi boş, bildirim atlanıyor");
            return;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(aliciEmail);
            mailMessage.setSubject(konu);
            mailMessage.setText(mesaj);
            mailSender.send(mailMessage);
            log.info("Gecikme e-postası gönderildi: {}", aliciEmail);
        } catch (Exception e) {
            log.error("E-posta gönderilirken hata oluştu, loglayıp devam ediyoruz: {}", e.getMessage());
        }
    }
}

