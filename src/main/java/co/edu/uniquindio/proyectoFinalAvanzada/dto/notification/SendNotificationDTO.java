package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record SendNotificationDTO (
        @NotBlank @Length(max = 600)String textComment,
        @NotBlank String idReport,
        @NotBlank String notificationTitle,
        @NotBlank String idUser,
        @Min(1) Double radiusKm
){

}
