package com.example.grooming_booking.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAppointmentConfirmation(String to, String date, String time, String service) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Potwierdzenie wizyty - Salon Groomerski Łapka & Nożyczki");

        message.setText(
                "Informujemy, że Twoja wizyta została zarezerwowana.\n\n" +
                        "Data: " + date + "\n" +
                        "Godzina: " + time + "\n" +
                        "Usługa: " + service + "\n\n" +
                        "Do zobaczenia w salonie!"

        );

        mailSender.send(message);
    }
}
