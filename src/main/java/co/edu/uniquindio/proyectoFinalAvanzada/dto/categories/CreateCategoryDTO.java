package co.edu.uniquindio.proyectoFinalAvanzada.dto.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CreateCategoryDTO(
        @NotBlank @Length(max = 50) String name,
        @NotBlank @Length(max = 700) String description
) {
}
