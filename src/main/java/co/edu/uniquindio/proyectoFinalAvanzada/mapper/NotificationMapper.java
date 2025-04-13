package co.edu.uniquindio.proyectoFinalAvanzada.mapper;


import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toDocument(SendNotificationDTO notificationDTO);


    NotificationDTO toDTO(Notification notification);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
    // De String a ObjectId
    default ObjectId map(String value) {
        return value != null ? new ObjectId(value) : null;
    }

}
