package co.edu.uniquindio.proyectoFinalAvanzada.model.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ValidationCode {
    private String code;
    private LocalDateTime date;
}
