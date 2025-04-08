package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notification")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Notification {

    @Id
    @EqualsAndHashCode.Include
    private String idNotification;

    private ObjectId idUser;
    private ObjectId idReport;
    private String notificationTitle;
    private String textComment;
    private boolean state;
}
