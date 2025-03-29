package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User {

    @Id
    private String id;

    private Rol rol;
    private String name;
    private String city;
    private String phone;
    private String email;
    private String address;
}
