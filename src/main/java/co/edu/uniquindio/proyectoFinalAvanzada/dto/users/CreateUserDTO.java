package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserDTO(
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        @NotBlank @Length(max = 100) String city,
        @NotBlank @Length(max = 100) String address,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password
) {
}
