package co.edu.uniquindio.proyectoFinalAvanzada.test;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired //para que sea inicializada automáticamente por Spring.
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void registrationTest() {
        // Crear una contraseña en texto plano
        String rawPassword = "clave123";

        // Cifrar la contraseña usando BCrypt
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        //Creamos un usuario con sus propiedades
        User user = User.builder()
                .rol(Rol.ADMIN)
                .name("Mariana Ramos")
                .city("Armenia")
                .phone("3207802997")
                .email("mrq@gmail.com")
                .password(encryptedPassword)
                .address("calle 11-45N")
                .build();

        //Guardamos el usuario en la base de datos
        User createdUser = userRepository.save(user);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(createdUser);

        // Verificamos que la contraseña cifrada no sea igual a la original
        assertNotEquals(rawPassword, createdUser.getPassword());

        // Verificamos que la contraseña original coincida con la cifrada
        assertTrue(passwordEncoder.matches(rawPassword, createdUser.getPassword()));
    }

    @Test
    public void updateTest(){
        //Definimos el id del usuario (de MongoDB)
        ObjectId id = new ObjectId("67f56e7bbf20ac0934efd8e4");

        //Obtenemos el usurio con el id XXXXXXX
        User user = userRepository.findById(String.valueOf(id)).orElseThrow();

        //Modificar el email del usuario
        user.setEmail("nuevoemail@email.com");

        //Guardamos nuevamente el usuario
        userRepository.save( user );

        //Obtenemos el usuario con el id XXXXXXX nuevamente
        User updatedUser = userRepository.findById(String.valueOf(id)).orElseThrow();;

        //Verificamos que el email se haya actualizado
        assertEquals("nuevoemail@email.com", updatedUser.getEmail());
    }

    @Test
    public void listAllTest(){
        //Obtenemos la lista de todos los usuarios (por ahora solo tenemos 1)
        List<User> list = userRepository.findAll();

        //Imprimimos los usuarios, se hace uso de una función lambda
        list.forEach(System.out::println);

        //Verificamos que la lista no esta vacia
        assertTrue(list.size() >= 1);;
    }
    @Test
    public void deleteTest(){
        //Definimos el id del usuario que queremos borrar
        ObjectId id = new ObjectId("67f56e7bbf20ac0934efd8e4");

        //Borramos el usuario con el id XXXXXXX
        userRepository.deleteById(String.valueOf(id));

        //Obtenemos el usuario con el id XXXXXXX
        User user = userRepository.findById(String.valueOf(id)).orElse(null);

        //Verificamos que el usuario no exista, debe ser null ya que fue eliminado
        assertNull(user);
    }

    @Test
    public void loginTest() {
        // Cifra la contraseña antes de guardar el usuario
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .rol(Rol.CLIENT)
                .name("Usuario Login")
                .city("Cali")
                .phone("1234567890")
                .email("login@test.com")
                .password(encodedPassword)
                .address("Carrera 1 #2-3")
                .build();

        userRepository.save(user);

        // Buscar el usuario por email
        Optional<User> resultado = userRepository.findByEmail("login@test.com");

        assertTrue(resultado.isPresent());

        // Validar que la contraseña ingresada coincida con la cifrada
        boolean passwordMatches = passwordEncoder.matches(rawPassword, resultado.get().getPassword());

        assertTrue(passwordMatches);
    }




}
