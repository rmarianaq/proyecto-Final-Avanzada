package co.edu.uniquindio.proyectoFinalAvanzada.model.vo;

import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class ReportHistory {

    private String observation;
    private LocalDateTime date;
    private ObjectId idUser;
    private ReportStatus status;
}
