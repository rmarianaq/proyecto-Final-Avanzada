package co.edu.uniquindio.proyectoFinalAvanzada.dto.notification;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;

import java.util.List;

public record SendNotificationDTO (
        String message,
        Report report,
        List<String> images
){

}
