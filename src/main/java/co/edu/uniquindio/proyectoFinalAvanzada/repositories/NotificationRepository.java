package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    // Buscar notificaciones por usuario
    List<Notification> findByIdUser(String idUser);


}
