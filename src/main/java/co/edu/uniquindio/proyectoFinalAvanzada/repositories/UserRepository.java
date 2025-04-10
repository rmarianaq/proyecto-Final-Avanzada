package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //Busca usuarios por el nombre y la ciudad
    @Query("{ 'name' : ?0, 'city' : ?1 }")
    List<User> findByNameCity(String name, String city);

    Optional<User> findByEmail(String email);

}
