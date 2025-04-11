package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {

    void createCategory(CreateCategoryDTO account);

    void updateCategory(UpdateCategoryDTO account);

    void deleteCategory(String id);

    CategoryDTO getCategory(String id);

    List<CategoryDTO> listAllReports();
}
