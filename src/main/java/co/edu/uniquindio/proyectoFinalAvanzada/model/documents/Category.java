package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

public class Category {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;
    private String description;

}
