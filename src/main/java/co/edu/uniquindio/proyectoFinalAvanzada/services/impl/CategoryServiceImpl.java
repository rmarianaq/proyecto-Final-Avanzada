package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.CreateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.categories.UpdateCategoryDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.CategoryNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.CategoryMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CategoryRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public void createCategory(CreateCategoryDTO account) throws Exception {

        Category category = categoryMapper.toDocument(account);
        boolean exist = categoryRepository.findByName(account.name()).isPresent();
        if(exist){
            throw new CategoryNotFoundException("El nombre "+account.name()+" ya está creado");
        }

        categoryRepository.save(category);

    }

    @Override
    public void updateCategory(UpdateCategoryDTO account) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(account.id())) {
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+account.id());
        }

        //Buscamos la categoria que se quiere actualizar
        ObjectId objectId = new ObjectId(account.id());
        Optional<Category> categoryOptional = categoryRepository.findById(String.valueOf(objectId));


        //Si no se encontró la categoria, lanzamos una excepción
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+account.id());
        }

        // Mapear los datos actualizados a la categoria existente
        Category category= categoryOptional.get();
        categoryMapper.toDocument(account, category);


        //Como el objeto categoria ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        categoryRepository.save(category);

    }

    @Override
    public void deleteCategory(String id) throws Exception{

        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+id);
        }

        //Buscamos la categoria que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Category> categoryOptional = categoryRepository.findById(String.valueOf(objectId));

        //Si no se encontró la categoria, lanzamos una excepción
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+id);
        }

        Category category= categoryOptional.get();
        categoryRepository.deleteById(String.valueOf(category));
    }

    @Override
    public CategoryDTO getCategory(String id) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+id);
        }

        //Buscamos la categoria que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Category> categoryOptional = categoryRepository.findById(String.valueOf(objectId));

        //Si no se encontró la categoria, lanzamos una excepción
        if(categoryOptional.isEmpty()){
            throw new CategoryNotFoundException("No se encontró la categoria con el id "+id);
        }

        //Retornamos la categoria encontrado convertido a DTO
        return categoryMapper.toDTO(categoryOptional.get());
    }

    @Override
    public List<CategoryDTO> listAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }
}
