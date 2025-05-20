package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UpdateUserDTO(
        @NotNull String id,
        @NotBlank @Length(max = 100) String name,
        @Length(max = 10) String phone,
        Municipality municipality,
        @NotBlank @Length(max = 100) String address) {
}
