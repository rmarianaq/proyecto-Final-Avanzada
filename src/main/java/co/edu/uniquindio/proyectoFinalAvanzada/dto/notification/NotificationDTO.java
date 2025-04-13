package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import java.time.LocalDateTime;

public record NotificationDTO(
    String id,
    String title,
    String message,
    boolean read,
    LocalDateTime date

) {
}
