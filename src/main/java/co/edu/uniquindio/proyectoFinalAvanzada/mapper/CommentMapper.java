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
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toDocument(CreateCommentDTO createCommentDTO);


    CommentDTO toDTO(Comment comment);
    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
