package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired //para que sea inicializada automáticamente por Spring.
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;

    /**
    @Test
    public void registrationTestWithService() throws Exception {
        // Crear DTO de registro
        CreateUserDTO dto = new CreateUserDTO(
                "Mariana Ramos",
                "mrq@gmail.com",
                "ARMENIA,
                "calle 11-45N",
                "3207802997",
                "clave123",
                Rol.ADMIN
        );

        // Ejecutar el método del servicio
        userService.createUser(dto);

        // Verificar que el usuario se guardó
        User user = userRepository.findByEmail("mrq@gmail.com").orElse(null);

        assertNotNull(user);
        assertNotEquals("clave123", user.getPassword());
        assertTrue(user.getPassword().startsWith("$2a$")); //BCrypt
    }

    @Test
    public void updateUserTestWithService() throws Exception {
        // Crear un usuario para luego actualizarlo
        User user = userRepository.save(User.builder()
                .name("Ana")
                .email("ana@mail.com")
                .password("clave123")
                .status(UserStatus.ACTIVE)
                .build());

        // Crear DTO con nuevos datos
            UpdateUserDTO updateDTO = new UpdateUserDTO(
                user.getId(), // id en string
                "Ana María",
                "ana_actualizada@mail.com",
                "BOGOTA,
                "Carrera 1",
                "3000000000"
        );

        // Ejecutar el método del servicio
        userService.updateUser(updateDTO);

        // Verificar el cambio
        User updated = userRepository.findById(user.getId()).orElseThrow();
        assertEquals("Ana María", updated.getName());
        assertEquals("ana_actualizada@mail.com", updated.getEmail());
    }

 */




}
