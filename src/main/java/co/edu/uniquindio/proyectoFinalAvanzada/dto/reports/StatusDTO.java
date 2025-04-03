package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record StatusDTO(
        @NotBlank String idUser,
        @NotBlank @Length(max = 1000) String reason,
        ReportStatus newStatus
) {
}
