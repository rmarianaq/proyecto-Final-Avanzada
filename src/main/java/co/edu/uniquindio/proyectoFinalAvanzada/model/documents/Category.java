package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class Category {
    @Id
    private String id;

    private String name;
    private String description;

}
