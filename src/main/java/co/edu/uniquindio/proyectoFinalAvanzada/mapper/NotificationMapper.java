package co.edu.uniquindio.proyectoFinalAvanzada.mapper;


import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "idNotification", ignore = true)
    @Mapping(target = "read", expression = "java(false)")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "idUser", source = "idUser", qualifiedByName = "mapStringToObjectId")
    @Mapping(target = "idReport", source = "idReport", qualifiedByName = "mapStringToObjectId")
    Notification toDocument(SendNotificationDTO notificationDTO);

    @Mapping(source = "idNotification", target = "id")
    @Mapping(source = "notificationTitle", target = "title")
    @Mapping(source = "textComment", target = "message")
    NotificationDTO toDTO(Notification notification);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
    // De String a ObjectId
// Metodo para mapear de String a ObjectId
    @Named("mapStringToObjectId")
    default ObjectId mapStringToObjectId(String value) {
        return value != null ? new ObjectId(value) : null;
    }
}
