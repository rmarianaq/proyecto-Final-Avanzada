package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CategoryRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.CategoryService;
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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private static String idCategoryCreate;

    @Test
    @Order(1)
    public void testCreateCategory() throws Exception {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO("Hurto","Reportes relacionados con hurtos");

        categoryService.createCategory(createCategoryDTO);

        List<Category> categories = categoryRepository.findAll();
        assertFalse(categories.isEmpty());

        idCategoryCreate = categories.getLast().getId(); //Guarda el id para las pruebas
        System.out.println("id asignado" + idCategoryCreate);
    }

    @Test
    @Order(2)
    public void testUpdateCategory() throws Exception {
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO(idCategoryCreate,"Robo actualizado", "Descripción editada");

        categoryService.updateCategory(updateCategoryDTO);

        Category updated = categoryRepository.findById(idCategoryCreate).orElseThrow();
        assertEquals("Robo actualizado", updated.getName());
        assertEquals("Descripción editada", updated.getDescription());
    }
    @Test
    @Order(3)
    public void testGetCategory() throws Exception {
        CategoryDTO dto = categoryService.getCategory(idCategoryCreate);
        assertNotNull(dto);
        assertEquals(idCategoryCreate, dto.id());
    }

    @Test
    @Order(4)
    public void testListAllCategories() {
        List<CategoryDTO> categories = categoryService.listAllCategories();
        assertFalse(categories.isEmpty());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        categoryService.deleteCategory(idCategoryCreate);
        Optional<Category> deleted = categoryRepository.findById(idCategoryCreate);
        assertTrue(deleted.isEmpty());
    }

}
