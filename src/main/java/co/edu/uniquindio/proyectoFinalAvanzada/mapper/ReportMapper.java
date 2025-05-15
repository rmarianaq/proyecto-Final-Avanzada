package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CreateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.UpdateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.Location;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "location", source = "location", qualifiedByName = "locationToGeoJsonPoint")
    Report toDocument(CreateReportDTO createReportDTO);


    @Mapping(source = "pictures", target = "pictures")
    @Mapping(source = "category", target = "category")
    ReportDTO toDTO(Report report);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    @Named("locationToGeoJsonPoint")
    default GeoJsonPoint locationToGeoJsonPoint(Location location){
      if (location == null){
        return null;
      }
      return new GeoJsonPoint(location.getLatitude(), location.getLongitude());
    }

  @Named("geoJsonPointToLocation")
  default GeoJsonPoint geoJsonPointToLocation(GeoJsonPoint location){
    if (location == null){
      return null;
    }
    return new GeoJsonPoint(location.getCoordinates().get(1), location.getCoordinates().get(2));
  }

  void toDocument(UpdateReportDTO updateReportDTO, @MappingTarget Report report);



}
