package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EmailServiceTest {

    private EmailService emailService;

    @BeforeEach
    public void setUp(){
        emailService = new EmailServiceImpl();

        // Configuración de valores simulados del application.properties
        ReflectionTestUtils.setField(emailService, "HOST", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailService, "PORT", 587);
        ReflectionTestUtils.setField(emailService, "USERNAME", "shielduq.help@gmail.com");
        ReflectionTestUtils.setField(emailService, "PASSWORD", "wrra ishf wbbo wydb");
    }

    @Test
    public void testSendEmail() {
        EmailDTO emailDTO = new EmailDTO("WELCOME TO SHIELD UQ",
                "Welcome to the SHIELD UQ team!\n" +
                        "This is where you'll stay informed about your surroundings and work toward our shared mission.\n" +
                        "Together, we'll build a better community for all.",
                "josephstoff2@gmail.com"
        );
        assertDoesNotThrow(() -> emailService.sendEmail(emailDTO));
    }

}
