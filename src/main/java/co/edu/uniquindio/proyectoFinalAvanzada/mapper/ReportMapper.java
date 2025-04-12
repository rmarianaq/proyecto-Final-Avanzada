package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CreateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.UpdateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    Report toDocument(CreateReportDTO createReportDTO);


    ReportDTO toDTO(Report report);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    void toDocument(UpdateReportDTO updateReportDTO, @MappingTarget Report report);
}
