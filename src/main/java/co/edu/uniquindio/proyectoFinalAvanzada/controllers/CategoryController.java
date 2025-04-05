package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    /*
    CRUD Categories
     */
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createCategory(@Valid @RequestBody CreateCategoryDTO account) throws Exception{
        categoryService.createCategory(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "La categoria de su reporte se ha creado exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateCategory(@Valid @RequestBody UpdateCategoryDTO account) throws Exception{
        categoryService.updateCategory(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Categoria editada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteCategory(@PathVariable String id)
            throws Exception{
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Categoria eliminada exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<CategoryDTO>> getCategory(@PathVariable String id) throws Exception{
        CategoryDTO info= categoryService.getCategory(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los reportes
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<MessageDTO<List<CategoryDTO>>> listAllCategories(){
        List<CategoryDTO>list= categoryService.listAllReports();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //
}
