package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CommentDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CreateCommentDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Comment;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateComment", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "idUser", source = "idUser", qualifiedByName = "mapStringToObjectId")
    @Mapping(target = "idReport", source = "idReport", qualifiedByName = "mapStringToObjectId")
    Comment toDocument(CreateCommentDTO createCommentDTO);


    @Mapping(source = "textComment", target = "message")
    CommentDTO toDTO(Comment comment);
    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    @Named("mapStringToObjectId")
    default ObjectId mapStringToObjectId(String value) {
        return value != null ? new ObjectId(value) : null;
    }
}
