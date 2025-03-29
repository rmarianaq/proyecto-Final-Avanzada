package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import jakarta.validation.constraints.NotBlank;

public record ActivateAccountDTO(
        @NotBlank String code
) {
}
