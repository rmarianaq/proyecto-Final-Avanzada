package co.edu.uniquindio.proyectoFinalAvanzada.dto.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record UpdateCategoryDTO(
        @NotNull String id,
        @NotBlank @Length(max = 100) String name,
        @NotNull List<@NotBlank String> icon

) {
}
