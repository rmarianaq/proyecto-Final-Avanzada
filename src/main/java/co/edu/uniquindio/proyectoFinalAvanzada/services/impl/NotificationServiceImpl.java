package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public List<NotificationDTO> listAllNotifications() {
        return null;
    }

    @Override
    public void markAsRead(String id, NotificationUpdateDTO account) {

    }

    @Override
    public void sendNotification(String id, SendNotificationDTO account) {

    }
}
