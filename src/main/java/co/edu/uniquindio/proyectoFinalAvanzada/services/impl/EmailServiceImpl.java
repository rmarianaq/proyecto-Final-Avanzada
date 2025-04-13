package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.host}")
    private String HOST;

    @Value("${spring.mail.port}")
    private int PORT;

    @Value("${spring.mail.username}")
    private String USERNAME;

    @Value("${spring.mail.password}")
    private String PASSWORD;

    @Override
    @Async
    public void sendEmail(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("shielduq.help@gmail.com")
                .to(emailDTO.receiver())
                .withSubject(emailDTO.subject())
                .withPlainText(emailDTO.body())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer(HOST, PORT, USERNAME, PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
