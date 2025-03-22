package co.edu.uniquindio.proyectoFinalAvanzada.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidationDTO(
        @NotBlank String field,
        @NotBlank String message
) {
}
