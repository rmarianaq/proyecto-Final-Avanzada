package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LocationFilterDTO(
        @Min(-90) @Max(90) Double latitude,
        @Min(-180) @Max(180) Double longitude,
        @Min(1) Double radiusKm
) {
}
