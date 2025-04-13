package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentDTO(
         String id,
         String idReport,
         String idUser,
         String message
) {
}
