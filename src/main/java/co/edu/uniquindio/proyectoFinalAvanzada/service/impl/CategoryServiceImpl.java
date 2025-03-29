package co.edu.uniquindio.proyectoFinalAvanzada.service.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public void createCategory(CreateCategoryDTO account) {

    }

    @Override
    public void updateCategory(UpdateCategoryDTO account) {

    }

    @Override
    public void deleteCategory(String id) {

    }

    @Override
    public CategoryDTO getCategory(String id) {
        return null;
    }

    @Override
    public List<CategoryDTO> listAllReports() {
        return null;
    }
}
