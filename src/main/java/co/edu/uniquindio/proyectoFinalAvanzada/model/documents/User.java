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
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @Builder
    public User(String id, Rol rol, UserStatus status, String name, String city, String phone, String email, String password, String address, Municipality municipality, ValidationCode validationCode, LocalDateTime registerDate, List<ObjectId> subscribeReports) {
        this.id = id;
        this.rol = rol;
        this.status = status;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.municipality = municipality;
        this.validationCode = validationCode;
        this.registerDate = registerDate;
        this.subscribeReports = subscribeReports;
    }
}
