package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record SendNotificationDTO (
        @NotBlank @Length(max = 600)String message,
        Report report,
        @NotBlank String idUser
){

}
