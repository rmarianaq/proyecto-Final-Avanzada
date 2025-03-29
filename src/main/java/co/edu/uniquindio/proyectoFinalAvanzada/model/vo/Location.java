package co.edu.uniquindio.proyectoFinalAvanzada.model.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Location {
    private double latitude;
    private double longitude;
}
