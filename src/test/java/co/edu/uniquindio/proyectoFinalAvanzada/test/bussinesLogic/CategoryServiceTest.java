package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CategoryRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.CategoryService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private static String idCategoryCreate = "67f58810cbfef4113e8268de";

    @Test
    public void testCreateCategory() throws Exception {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO("Hurto", "Reportes relacionados con hurtos");

        categoryService.createCategory(createCategoryDTO);

        List<Category> categories = categoryRepository.findAll();
        assertFalse(categories.isEmpty());

        idCategoryCreate = categories.getLast().getId(); //Guarda el id para las pruebas
        System.out.println("id asignado" + idCategoryCreate);
    }

    @Test
    public void testUpdateCategory() throws Exception {
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO("682cd9d909453c57ad5c46f8", "Hurto actualizado", "Descripcion actualizada");

        categoryService.updateCategory(updateCategoryDTO);
    }
    @Test
    void testDeleteCategory() throws Exception {
        // Arrange
        String id = "682cd9d909453c57ad5c46f8";
        ObjectId objectId = new ObjectId(id);

        Category category = new Category();
        category.setId(objectId.toString());

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        // Act
        categoryService.deleteCategory(id);

        // Assert
        verify(categoryRepository, times(1)).deleteById(String.valueOf(category));
    }


}