package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CommentDTO(
         String id,
         String idReport,
         String idUser,
         String message,
         LocalDateTime date
) {
}
