package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    Category toDocument(CreateCategoryDTO createCategoryDTO);


    CategoryDTO toDTO(Category category);
    void toDocument(UpdateCategoryDTO updateCategoryDTO, @MappingTarget Category category);
    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

}
