package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Buscar usuario por correo
    @Query("{ 'email' : ?0 }")
    Optional<User> searchUserByEmail(String email);

    // Buscar usuario por nombre de usuario
    @Query("{ 'username' : ?0 }")
    Optional<User> searchUserByUsername(String username);

    // Buscar usuarios con más de X reportes
    @Query("{ 'reports' : { $size : { $gt: ?0 } } }")
    List<User> searchUserByrangeOfXReports(int amount);
}
