package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "user-created category", description = "allows the user create category")
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createCategory(@Valid @RequestBody CreateCategoryDTO account) throws Exception{
        categoryService.createCategory(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "La categoria de su reporte se ha creado exitosamente"));
    }

    @Operation(summary = "user-updated category", description = "allows the user to update their category")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateCategory(@Valid @RequestBody UpdateCategoryDTO account) throws Exception{
        categoryService.updateCategory(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Categoria editada exitosamente"));
    }

    @Operation(summary = "user-delate category", description = "allows the user to update their category")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteCategory(@PathVariable String id)
            throws Exception{
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Categoria eliminada exitosamente"));
    }

    @Operation(summary = "Extract information from the category", description = "Allows extracting information from the created categories")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<CategoryDTO>> getCategory(@PathVariable String id) throws Exception{
        CategoryDTO info= categoryService.getCategory(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los reportes
     */
    @Operation(summary = "list all categories", description = "llows listing all created categories")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<MessageDTO<List<CategoryDTO>>> listAllCategories(){
        List<CategoryDTO>list= categoryService.listAllCategories();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //
}
