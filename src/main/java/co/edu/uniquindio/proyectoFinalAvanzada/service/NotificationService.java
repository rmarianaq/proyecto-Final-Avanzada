package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> listAllNotifications();

    void markAsRead(String id, NotificationUpdateDTO account);
}
