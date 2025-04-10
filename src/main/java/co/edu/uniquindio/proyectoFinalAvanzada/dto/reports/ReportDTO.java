package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReportDTO(
        String id,
        String title,
        String description,
        Double latitude,
        Double longitude,
        List<String> image,
        String idCategory,
        ReportStatus status,
        LocalDateTime date,
        String userId
) {
}
