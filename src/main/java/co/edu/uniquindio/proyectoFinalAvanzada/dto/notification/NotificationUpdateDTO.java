package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import jakarta.validation.constraints.NotBlank;

public record NotificationUpdateDTO(
        @NotBlank boolean read
) {
}
