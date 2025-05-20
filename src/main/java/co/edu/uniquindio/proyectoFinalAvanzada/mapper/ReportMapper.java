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

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "location", source = "location", qualifiedByName = "locationToGeoJsonPoint")
    @Mapping(target = "category", source = "category", qualifiedByName = "mapStringToObjectId")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "idUser", source = "idUser", qualifiedByName = "mapStringToObjectId")
    @Mapping(target = "important", ignore = true)
    @Mapping(target = "usersWhoMarkedImportant", expression = "java(new java.util.HashSet<>())")
    Report toDocument(CreateReportDTO createReportDTO);


    @Mapping(source = "pictures", target = "pictures")
    @Mapping(source = "category", target = "category", qualifiedByName = "mapObjectIdListToStringList")
    @Mapping(source = "location", target = "location", qualifiedByName = "geoJsonPointToLocation")
    ReportDTO toDTO(Report report);


    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "location", source = "location", qualifiedByName = "locationToGeoJsonPoint")
    @Mapping(target = "category", source = "category", qualifiedByName = "mapStringToObjectId")
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "important", ignore = true)
    @Mapping(target = "usersWhoMarkedImportant", expression = "java(new java.util.HashSet<>())")
    void toDocument(UpdateReportDTO updateReportDTO, @MappingTarget Report report);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    @Named("mapStringToObjectId")
    default ObjectId mapStringToObjectId(String value){
        return value != null ? new ObjectId(value) : null;
    }
    @Named("mapObjectIdToString")
    default String mapObjectIdToString(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    @Named("mapStringListToObjectIdList")
    public default List<ObjectId> mapStringListToObjectIdList(List<String> list) {
        return list == null ? null : list.stream()
                .map(this::mapStringToObjectId)
                .toList();
    }

    @Named("mapObjectIdListToStringList")
    public default List<String> mapObjectIdListToStringList(List<ObjectId> list) {
        return list == null ? null : list.stream()
                .map(this::mapObjectIdToString)
                .toList();
    }

    @Named("locationToGeoJsonPoint")
    default GeoJsonPoint locationToGeoJsonPoint(Location location){
      if (location == null) return null;
      return new GeoJsonPoint(location.getLongitude(), location.getLatitude());
    }

    @Named("geoJsonPointToLocation")
    default Location geoJsonPointToLocation(GeoJsonPoint geoJsonPoint){
        if (geoJsonPoint == null) return null;
        return new Location(
                geoJsonPoint.getCoordinates().get(1),
                geoJsonPoint.getCoordinates().get(0));
    }





}
