package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;


import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CreateReportDTO(
        @NotBlank @Length(max = 300) String title,
        @NotBlank @Length(max = 1000) String description,
        @Min(-90) @Max(90) Double latitude,
        @Min(-180) @Max(180) Double longitude,
        @NotNull List<@NotBlank String> pictures,
        @NotBlank String idCategory,
        ReportStatus status
) {
}
