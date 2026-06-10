package com.example.grooming_booking.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAppointmentConfirmation(
            String to,
            String date,
            String time,
            String service,
            String cancelLink,
            String editLink
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("Potwierdzenie wizyty - Salon Groomerski Łapka & Nożyczki");

        String html =
                "<html><body>" +
                        "Twoja wizyta została zarezerwowana.<br><br>" +

                        "Data: " + date + "<br>" +
                        "Godzina: " + time + "<br>" +
                        "Usługa: " + service + "<br><br>" +

                        "Zarządzaj wizytą:<br>" +
                        "<a href='" + cancelLink + "'>Anuluj wizytę</a><br><br>" +

                        "Dziękujemy!" +
                        "</body></html>";

        helper.setText(html, true);

        mailSender.send(message);
    }

    public void sendAppointmentUpdated(String to, String date, String time, String service) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Zmiana wizyty - Salon Groomerski Łapka & Nożyczki");

        message.setText(
                "Twoja wizyta została zmieniona.\n\n" +
                        "Nowa data: " + date + "\n" +
                        "Nowa godzina: " + time + "\n" +
                        "Usługa: " + service + "\n\n" +
                        "Do zobaczenia w salonie!"
        );

        mailSender.send(message);
    }

    public void sendAppointmentCancelled(String to, String date, String time, String service) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Anulowanie wizyty - Salon Groomerski Łapka & Nożyczki");

        message.setText(
                "Twoja wizyta została anulowana.\n\n" +
                        "Data: " + date + "\n" +
                        "Godzina: " + time + "\n" +
                        "Usługa: " + service + "\n\n" +
                        "Jeśli chcesz zarezerwować nowy termin zapraszamy ponownie!"
        );

        mailSender.send(message);
    }
}