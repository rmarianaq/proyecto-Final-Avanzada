package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

public record UpdateUserDTO(
        @NotNull String idUser,
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        Municipality municipality,
        @NotBlank @Length(max = 100) String address
) {
}
