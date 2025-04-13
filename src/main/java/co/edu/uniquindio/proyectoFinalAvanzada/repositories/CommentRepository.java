package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
