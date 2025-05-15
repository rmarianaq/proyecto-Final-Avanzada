package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "reports")
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Report {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String title;
    private List<ObjectId> category;
    private String city;
    private String description;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)//permite que Mongo cree un índice especial que entiende latitud/longitud, necesario para consultas $near.
    private GeoJsonPoint location;
    private LocalDateTime date;
    private List<String> pictures;
    private User user;
    private ReportStatus status;
    private Integer important = 0;
    private Set<String> usersWhoMarkedImportant = new HashSet<>();//El Set evita duplicados automáticamente

    @Builder
    public Report(String id, String title, List<ObjectId> category, String city, String description, GeoJsonPoint location, LocalDateTime date, List<String> pictures, User user, ReportStatus status, Integer important, Set<String> usersWhoMarkedImportant) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.city = city;
        this.description = description;
        this.location = location;
        this.date = date;
        this.pictures = pictures;
        this.user = user;
        this.status = status;
        this.important = important;
        this.usersWhoMarkedImportant = usersWhoMarkedImportant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ObjectId> getCategory() {
        return category;
    }

    public void setCategory(List<ObjectId> category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Integer getImportant() {
        return important;
    }

    public void setImportant(Integer important) {
        this.important = important;
    }

    public Set<String> getUsersWhoMarkedImportant() {
        return usersWhoMarkedImportant;
    }

    public void setUsersWhoMarkedImportant(Set<String> usersWhoMarkedImportant) {
        this.usersWhoMarkedImportant = usersWhoMarkedImportant;
    }
}
