package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserDTO(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        Municipality municipality,
        @NotBlank @Length(max = 100) String address,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 10) String password,

        Rol client) {
}
