package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.NotificationRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.NotificationService;
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

   private static String idNotificationCreate = "00988876a";

   private static String idUser = "123nvhdjl234sak2009";

   @Test
    public void testSendNotification() {

       SendNotificationDTO dto = new SendNotificationDTO(
               "Hay un evento que puede ser de tu interés.",
               null, // Aquí deberías poner un objeto Report válido si es necesario
               idUser,
               5.0  // Por ejemplo, 5 km
       );

       notificationService.sendNotification(idUser, dto);

       //Envía la notificación
       notificationService.sendNotification(idUser, dto);

       List<Notification> list = notificationRepository.findAll();
       assertFalse(list.isEmpty());

       Notification last = list.getLast();
       idNotificationCreate = last.getIdNotification();

       assertEquals("Alerta de su interes", last.getNotificationTitle());
       assertFalse(last.isRead(), "El estado debe ser false despues de crear la notificiación");

    }

    @Test
    public void testSendNotification2() {
        SendNotificationDTO dto = new SendNotificationDTO(
                "Mensaje de prueba",
                null,            // Puedes colocar un objeto Report válido si es necesario
                idNotificationCreate, // ID del usuario
                3.0                   // Radio en km
        );

        notificationService.sendNotification(idNotificationCreate, dto);
    }
}
