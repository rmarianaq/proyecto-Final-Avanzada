package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.ValidationCode;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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
    private Municipality municipality;
    private ValidationCode validationCode;
    private LocalDateTime registerDate;

    private List<ObjectId> subscribeReports;
}
