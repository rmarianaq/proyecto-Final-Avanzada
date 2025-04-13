package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(CreateCategoryDTO account) throws Exception;

    void updateCategory(UpdateCategoryDTO account) throws Exception;

    void deleteCategory(String id) throws Exception;

    CategoryDTO getCategory(String id) throws Exception;

    List<CategoryDTO> listAllCategories();
}
