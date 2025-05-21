package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.Location;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record UpdateReportDTO(
        @NotNull String id,
        @NotBlank @Length(max = 300) String title,
        @NotBlank @Length(max = 1000) String description,
        Location location,
        @NotNull List<@NotBlank String> pictures,
        @NotNull List<@NotBlank String> category,
        ReportStatus status,
        Municipality municipality
) {
}
