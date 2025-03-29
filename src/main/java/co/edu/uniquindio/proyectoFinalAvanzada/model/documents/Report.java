package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;

import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.Location;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Report {

    @Id
    private String id;

    private String title;
    private ObjectId category;
    private String city;
    private String description;
    private Location location;
    private Date date;
    private List<String> pictures;

}
