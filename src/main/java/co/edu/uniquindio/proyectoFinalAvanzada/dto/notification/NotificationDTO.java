package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import java.time.LocalDateTime;

public record NotificationDTO(
    String idNotification,
    String notificationTitle,
    String text,
    boolean read,
    LocalDateTime date

) {
}
