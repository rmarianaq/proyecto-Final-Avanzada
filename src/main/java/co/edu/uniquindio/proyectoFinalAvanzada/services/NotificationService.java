package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> listAllNotifications();

    void markAsRead(String id, NotificationUpdateDTO account);

    void sendNotification(String id, SendNotificationDTO account);

    void notifyFollowers(Report report, String message) throws Exception;

}
