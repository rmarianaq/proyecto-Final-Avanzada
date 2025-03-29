package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.util.Date;

@Document("comment")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Comment {

    @Id
    private String idComment;

    private ObjectId idUser;
    private ObjectId idReports;
    private String textComment;
    private Date dateComment;

}
