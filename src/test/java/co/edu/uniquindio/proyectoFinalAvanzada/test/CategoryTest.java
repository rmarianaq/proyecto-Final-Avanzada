package co.edu.uniquindio.proyectoFinalAvanzada.test;


import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CategoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CategoryTest {

    @Autowired //para que sea inicializada automáticamente por Spring.
    private CategoryRepository categoryRepository;

    @Test
    public void createTest() {
        //Creamos una categoria con sus propiedades
        Category category = Category.builder()
                .name("Mascotas")
                .description("Maltrato animal")
                .build();

        //Guardamos la categoria en la base de datos
        Category savedCategory = categoryRepository.save(category);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(savedCategory);
    }

    @Test
    public void updateTest(){
        //Definimos el id de la categoria (de MongoDB)
        ObjectId id = new ObjectId("67f58810cbfef4113e8268de");

        //Obtenemos la categoria con el id XXXXXXX
        Category category = categoryRepository.findById(String.valueOf(id)).orElseThrow();

        //Modificar la descripcion de la categoria
        category.setDescription("Fuego de grandes proporciones sin control");

        //Guardamos nuevamente la categoria
        categoryRepository.save( category );

        //Obtenemos la categoria con el id XXXXXXX nuevamente
        Category updatedCategory = categoryRepository.findById(String.valueOf(id)).orElseThrow();;

        //Verificamos que la descripcion se haya actualizado
        assertEquals("Fuego de grandes proporciones sin control", updatedCategory.getDescription());
    }

    @Test
    public void listAllTest(){
        //Obtenemos la lista de todos las categorias (por ahora solo tenemos 1)
        List<Category> list = categoryRepository.findAll();

        //Imprimimos las categorias, se hace uso de una función lambda
        list.forEach(System.out::println);

        //Verificamos que la lista no esta vacia
        assertTrue(list.size() >= 1);;
    }
    @Test
    public void deleteTest(){
        //Definimos el id de la categoria que queremos borrar
        ObjectId id = new ObjectId("67f58810cbfef4113e8268de");

        //Borramos la categoria con el id XXXXXXX
        categoryRepository.deleteById(String.valueOf(id));

        //Obtenemos la categoria con el id XXXXXXX
        Category category = categoryRepository.findById(String.valueOf(id)).orElse(null);

        //Verificamos que la categoria no exista, debe ser null ya que fue eliminado
        assertNull(category);
    }
}
