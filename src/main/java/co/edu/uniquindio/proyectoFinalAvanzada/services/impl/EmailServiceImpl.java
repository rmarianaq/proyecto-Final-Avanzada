package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import org.springframework.stereotype.Service;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    @Async
    public void sendEmail(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("SMTP_USERNAME")
                .to(emailDTO.receiver())
                .withSubject(emailDTO.subject())
                .withPlainText(emailDTO.body())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "marianaxzy.115@gmail.com", "pewhodbamecbftvc")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }

    }
}
