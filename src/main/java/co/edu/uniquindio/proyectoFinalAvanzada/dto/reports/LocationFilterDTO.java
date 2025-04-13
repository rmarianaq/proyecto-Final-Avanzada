package co.edu.uniquindio.proyectoFinalAvanzada.dto.reports;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LocationFilterDTO(
       Double latitude,
       Double longitude,
       Double radiusKm,

       int page
) {
}
