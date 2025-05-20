package co.edu.uniquindio.proyectoFinalAvanzada.dto.users;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;

public record UserDTO(
        String id,
        Municipality municipality,
        String name
) {
}
