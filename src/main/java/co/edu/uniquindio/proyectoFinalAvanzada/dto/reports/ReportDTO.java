package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.Location;

import java.time.LocalDateTime;
import java.util.List;

public record ReportDTO(
        String id,
        String title,
        String description,
        Location location,
        List<String> pictures,
        List<String> category,
        ReportStatus status,
        LocalDateTime date
) {
}
