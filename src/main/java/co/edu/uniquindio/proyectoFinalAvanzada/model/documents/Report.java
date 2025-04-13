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
@Builder
@NoArgsConstructor
@Setter
@Getter
@ToString
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
}
