package co.edu.uniquindio.proyectoFinalAvanzada.model.documents;


import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.ValidationCode;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
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
    private String phone;
    private String email;
    private String password;
    private String address;
    private Municipality municipality;
    private ValidationCode validationCode;
    private LocalDateTime registerDate;

    private List<String> followedReports = new ArrayList<>();

    @Builder
    public User(String id, Rol rol, UserStatus status, String name, String phone, String email, String password, String address, Municipality municipality, ValidationCode validationCode, LocalDateTime registerDate, List<String> followedReports) {
        this.id = id;
        this.rol = rol;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.municipality = municipality;
        this.validationCode = validationCode;
        this.registerDate = registerDate;
        this.followedReports = followedReports;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public ValidationCode getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(ValidationCode validationCode) {
        this.validationCode = validationCode;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public List<String> getFollowedReports() {
        return followedReports;
    }

    public void setFollowedReports(List<String> followedReports) {
        this.followedReports = followedReports;
    }
}
