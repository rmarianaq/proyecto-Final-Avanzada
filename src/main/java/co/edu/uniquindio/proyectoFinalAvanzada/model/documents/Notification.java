package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {

    @Id
    @EqualsAndHashCode.Include
    private String idNotification;

    private ObjectId idUser;
    private ObjectId idReport;
    private String notificationTitle;
    private String text;
    private boolean read;
    private LocalDateTime date;

    public  Notification(){}

    @Builder
    public Notification(String idNotification, ObjectId idUser, ObjectId idReport, String notificationTitle, String text, boolean read, LocalDateTime date) {
        this.idNotification = idNotification;
        this.idUser = idUser;
        this.idReport = idReport;
        this.notificationTitle = notificationTitle;
        this.text = text;
        this.read = read;
        this.date = date;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
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

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
