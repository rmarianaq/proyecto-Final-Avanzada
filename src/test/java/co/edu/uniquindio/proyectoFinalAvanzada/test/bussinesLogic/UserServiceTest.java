package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserFilterDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.UserNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.UserMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.ValidationCode;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.UserService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static String userId = "682cd7c9b61a3521201b16ac";

    private final String testEmail = "teo154@utlook.com";

    @Test
    public void testUserCreate() throws Exception {
        CreateUserDTO dto = new CreateUserDTO(
                "Juan Pérez",
                "3115558888",
                Municipality.ARMENIA,
                "Calle 123",
                testEmail,
                "password123",
                Rol.CLIENT
        );

        userService.createUser(dto);

        Optional<User> userOptional = userRepository.findByEmail(testEmail);
        assertTrue(userOptional.isPresent());

        User user = userOptional.get();
        userId = user.getId(); // Guardamos para pruebas posteriores
        assertEquals(UserStatus.INACTIVE, user.getStatus()); // Si el estado por defecto es INACTIVE
    }

    @Test
    public void testGetUser() throws Exception {
        UserDTO userDTO = userService.getUser(userId);
        assertNotNull(userDTO);
        assertEquals("Juan Pérez", userDTO.name());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UpdateUserDTO updateDTO = new UpdateUserDTO(
                userId,
                "Juan Actualizado",
                "3121234567",
                Municipality.CALARCA,
                "Carrera 45"
        );

        userService.updateUser(updateDTO);

        User user = userRepository.findById(userId).orElseThrow();
        assertEquals("Juan Actualizado", user.getName());
        assertEquals("Carrera 45", user.getAddress());
    }

    @Test
    public void testListarUsuarios() {
        UserFilterDTO filter = new UserFilterDTO("Juan", "Armenia", 0);
        List<UserDTO> usuarios = userService.listAllUsers(filter);
        assertFalse(usuarios.isEmpty());

        // Mostrar resultados
        usuarios.forEach(System.out::println);
    }

    @Test
    public void testUserDelete() throws Exception {
        userService.deleteUser(userId);
        User user = userRepository.findById(userId).orElseThrow();
        assertEquals(UserStatus.DELETED, user.getStatus());
    }

    @Test
    public void testChangePassword() throws Exception {
        // Buscar el usuario de prueba por su email
        User user = userRepository.findByEmail(testEmail).orElseThrow(() ->
                new RuntimeException("Usuario con email de prueba no encontrado"));

        String newPassword = "nuevaClave123";

        // Ejecutar el cambio de contraseña
        userService.changePassword(user.getEmail(), user.getValidationCode().getCode(), newPassword);

        // Recuperar el usuario actualizado desde la base de datos
        User actualizado = userRepository.findById(user.getId()).orElseThrow();

        // Verificar que la contraseña se haya actualizado
        assertEquals(newPassword, actualizado.getPassword());

        // Mostrar por consola
        System.out.println("Contraseña actualizada correctamente para el usuario: " + actualizado.getEmail());
    }

    @Test
    public void testActivateAccount() throws Exception {

        User user = userRepository.findByEmail(testEmail).orElseThrow();

        // Simula que se le ha asignado un código de validación válido y reciente
        String codigoValido = "123456";
        user.setValidationCode(new ValidationCode(codigoValido, LocalDateTime.now()));
        userRepository.save(user);


        userService.activateAccount(testEmail, codigoValido);

        // Verifica que el estado ahora es ACTIVO
        User actualizado = userRepository.findByEmail(testEmail).orElseThrow();
        assertEquals(UserStatus.ACTIVE, actualizado.getStatus());

        System.out.println("Cuenta activada correctamente para: " + actualizado.getEmail());
    }

    @Test
    public void testSendVerificationCode() throws Exception {

        User userAntes = userRepository.findByEmail(testEmail).orElseThrow();
        assertNull(userAntes.getValidationCode(), "Debe empezar sin código de validación");


        userService.sendVerificationCode(testEmail);

        // Recuperamos el usuario actualizado
        User userDespues = userRepository.findByEmail(testEmail).orElseThrow();

        // Verificamos que se haya asignado un código de validación
        ValidationCode code = userDespues.getValidationCode();
        assertNotNull(code, "El código de validación no debe ser nulo");
        assertNotNull(code.getCode(), "Debe tener un valor de código");
        assertNotNull(code.getDate(), "Debe tener una fecha de asignación");
        assertEquals(9, code.getCode().length(), "El código debe tener 9 caracteres");

        // Mensaje para consola
        System.out.println("Código enviado correctamente: " + code.getCode());
    }
}




