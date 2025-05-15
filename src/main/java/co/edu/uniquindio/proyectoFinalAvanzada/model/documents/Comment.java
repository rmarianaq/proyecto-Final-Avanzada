package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "comments")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Comment {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idUser;
    private ObjectId idReport;
    private String textComment;
    private LocalDateTime dateComment;

    @Builder
    public Comment(String id, ObjectId idUser, ObjectId idReport, String textComment, LocalDateTime dateComment) {
        this.id = id;
        this.idUser = idUser;
        this.idReport = idReport;
        this.textComment = textComment;
        this.dateComment = dateComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }

    public ObjectId getIdReport() {
        return idReport;
    }

    public void setIdReport(ObjectId idReport) {
        this.idReport = idReport;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public LocalDateTime getDateComment() {
        return dateComment;
    }

    public void setDateComment(LocalDateTime dateComment) {
        this.dateComment = dateComment;
    }
}
