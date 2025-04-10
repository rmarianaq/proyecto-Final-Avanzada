package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

public class User {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Rol rol;
    private UserStatus status;
    private String name;
    private String city;
    private String phone;
    private String email;
    private String password;
    private String address;
}
