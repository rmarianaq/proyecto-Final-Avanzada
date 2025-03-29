package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ChangePasswordDTO(
        @NotBlank String code,
        @NotBlank @Length(min = 7, max = 20) String newPass
) {
}
