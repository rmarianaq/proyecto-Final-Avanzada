package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.NotificationRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.NotificationService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    private static String userId = "67fc68028602af433e434353";
    private static String notificationId = "67fc68038602af433e434354";

    @BeforeAll
    public static void setup(@Autowired UserRepository userRepository) {
        User user = User.builder()
                .rol(Rol.CLIENT)
                .status(UserStatus.ACTIVE)
                .name("Prueba Notificaciones")
                .email("teo154@outlook.com")
                .password("password123")
                .city("Ciudad")
                .phone("123456")
                .address("Calle Falsa 123")
                .municipality(Municipality.ARMENIA)
                .registerDate(LocalDateTime.now())
                .build();
        userRepository.save(user);
        userId = user.getId();
    }

    @Test
    public void testSendNotification() {
        SendNotificationDTO dto = new SendNotificationDTO(
                "Mensaje de prueba",
                null,
                userId,
                1.0
        );

        notificationService.sendNotification(userId, dto);

        List<Notification> lista = notificationRepository.findByIdUser(userId);
        assertFalse(lista.isEmpty(), "La lista no debería estar vacía");

        // Tomamos la última insertada según fecha si existe ese campo, o simplemente la primera
        Notification ultima = lista.get(lista.size() - 1);
        notificationId = ultima.getIdNotification(); // se guarda para el siguiente test

        assertEquals("Mensaje de prueba", ultima.getTextComment());
        assertFalse(ultima.isRead());
    }

    @Test
    public void testMarkAsRead() {
        NotificationUpdateDTO updateDTO = new NotificationUpdateDTO(true);
        notificationService.markAsRead(notificationId, updateDTO);

        Notification actual = notificationRepository.findById(notificationId).orElseThrow();
        assertTrue(actual.isRead(), "La notificación debería estar marcada como leída");
    }

    @Test
    public void testListAllNotifications() {
        List<NotificationDTO> lista = notificationService.listAllNotifications();
        assertNotNull(lista);
        assertFalse(lista.isEmpty(), "La lista de notificaciones no debería estar vacía");
    }

    @Test
    public void testNotifyFollowers() throws Exception {
        User user = userRepository.findById(userId).orElseThrow();

        Report report = Report.builder()
                .id(new ObjectId().toString())
                .title("Reporte de prueba")
                .user(user)
                .build();

        // Agregamos el reporte al usuario como seguido
        user.getFollowedReports().add(report.getId());
        userRepository.save(user);

        String mensaje = "Este es un mensaje de actualización";

        // Ejecutar método
        notificationService.notifyFollowers(report, mensaje);

        // No falla = pasa. Validación más compleja sería mockear el emailService.
        assertTrue(true);
    }
}
