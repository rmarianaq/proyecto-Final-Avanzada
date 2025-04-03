package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentDTO(
        @NotBlank String idUser,
        @NotBlank @Length(max = 1000) String message
) {
}
