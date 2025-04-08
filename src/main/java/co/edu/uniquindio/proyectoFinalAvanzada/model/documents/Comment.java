package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.util.Date;

@Document(collection = "comments")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Comment {

    @Id
    @EqualsAndHashCode.Include
    private String idComment;

    private ObjectId idUser;
    private ObjectId idReports;
    private String textComment;
    private Date dateComment;

}
