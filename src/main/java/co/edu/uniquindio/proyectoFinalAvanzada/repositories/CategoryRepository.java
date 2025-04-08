package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
