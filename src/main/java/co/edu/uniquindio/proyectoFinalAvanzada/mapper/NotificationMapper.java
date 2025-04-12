package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    //Mapeo de Notificación -> NotificacionDTO (solo ID)
    @Mapping(target = "id", source = "idNotification")
    Notification toDocument(NotificationDTO notificationDTO);

    NotificationDTO toDto(Notification notification);


    //Mapeo de NotificaciónDTO -> Notificacion (solo ID)
    @Mapping(target = "idNotification", source = "id")
    Notification toEntity(NotificationDTO dto);

    void toDocument(NotificationUpdateDTO notificationUpdateDTO, @MappingTarget Notification notification);

}
