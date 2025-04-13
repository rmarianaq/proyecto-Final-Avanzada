package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Rol;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.ValidationCode;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@SpringBootTest
public class UserServiceTest {
    @Autowired //para que sea inicializada automáticamente por Spring.
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void registrationFailsIfEmailAlreadyExists() {
        // Arrange: Guardar un usuario con un email existente
        userRepository.save(User.builder()
                .name("Laura")
                .email("laura@mail.com")
                .password(passwordEncoder.encode("123456"))
                .status(UserStatus.ACTIVE)
                .build());

        CreateUserDTO dto = new CreateUserDTO(
                "Laura",
                "laura@mail.com", // mismo correo
                Municipality.ARMENIA,
                "Calle 5 #10-20",
                "3210000000",
                "clave123",
                Rol.CLIENT
        );

        // Act & Assert: Se espera una excepción
        Exception ex = assertThrows(Exception.class, () -> userService.createUser(dto));
        assertEquals("El correo ya está registrado.", ex.getMessage());
    }


    @Test
    public void updateUserFailsIfUserNotFound() {
        // Arrange: Crear un DTO con ID inexistente
        UpdateUserDTO dto = new UpdateUserDTO(
                "id-falso-no-existe",
                "Nombre Nuevo",
                "3000000000",
                Municipality.MEDELLIN,
                "Calle 123",

                "3109876543");

        // Act & Assert: Se espera una excepción
        Exception ex = assertThrows(Exception.class, () -> userService.updateUser(dto));
        assertEquals("El usuario con el ID id-falso-no-existe no existe.", ex.getMessage());
    }


    @Test
    public void registrationFailsIfPasswordTooShort() {
        CreateUserDTO dto = new CreateUserDTO(
                "Santi",
                "santi@mail.com",
                Municipality.PEREIRA,
                "Calle 8",
                "3010000000",
                "123", // contraseña corta
                Rol.CLIENT
        );

        Exception ex = assertThrows(Exception.class, () -> userService.createUser(dto));
        assertEquals("La contraseña debe tener al menos 6 caracteres.", ex.getMessage());
    }

    @Test
    public void registrationFailsIfIdAlreadyExists() {
        // Crear un usuario con ID fijo
        User user = userRepository.save(User.builder()
                .id("1123445670")
                .name("Daniela")
                .email("daniela@mail.com")
                .password(passwordEncoder.encode("clave123"))
                .status(UserStatus.ACTIVE)
                .build());

        // Crear DTO que intente usar el mismo ID
        User usuarioConIdExistente = User.builder()
                .id("1123445670")
                .name("Copia")
                .email("otra@mail.com")
                .password(passwordEncoder.encode("clave123"))
                .status(UserStatus.ACTIVE)
                .build();

        // Simular intento manual de guardar ese objeto con ID duplicado
        Exception ex = assertThrows(Exception.class, () -> {
            userRepository.save(usuarioConIdExistente); // Esto lanzará DuplicateKeyException
        });

        // Validación básica del tipo de excepción
        assertTrue(ex.getMessage().contains("duplicate key") || ex instanceof org.springframework.dao.DuplicateKeyException);
    }

    @Test
    public void getUserFailsIfEmailDoesNotExist() {
        String emailInexistente = "noexiste@mail.com";

        Exception ex = assertThrows(Exception.class, () -> {
            userService.sendVerificationCode(emailInexistente);
        });

        assertEquals("No existe una cuenta registrada con ese correo.", ex.getMessage());
    }

    //actualizar datos
    @Test
    public void updateUserCorrectlyUpdatesUserData() throws Exception {
        // Arrange: Crear un usuario inicial
        User user = userRepository.save(User.builder()
                .name("Carlos Pérez")
                .email("carlos@mail.com")
                .city("MEDELLIN")
                .address("Calle 50")
                .phone("3001234567")
                .password(passwordEncoder.encode("password123"))
                .status(UserStatus.ACTIVE)
                .build());

        // Crear un DTO con los nuevos datos del usuario
        UpdateUserDTO updateDTO = new UpdateUserDTO(
                user.getId(),                 // ID del usuario existente
                "Carlos Andrés Pérez",        // Nuevo nombre
                "andres@mail.com",            // Nuevo correo
                Municipality.BOGOTA,          // Nueva ciudad
                "Carrera 70 #20-40",          // Nueva dirección
                "3109876543"                  // Nuevo teléfono
        );

        // Act: Llamar al servicio para actualizar
        userService.updateUser(updateDTO);

        // Assert: Consultar el usuario actualizado y verificar cambios
        User updatedUser = userRepository.findById(user.getId()).orElseThrow();

        assertEquals("Carlos Andrés Pérez", updatedUser.getName());
        assertEquals("andres@mail.com", updatedUser.getEmail());
        assertEquals("BOGOTA", updatedUser.getCity());
        assertEquals("Carrera 70 #20-40", updatedUser.getAddress());
        assertEquals("3109876543", updatedUser.getPhone());

    }

    //eliminar ususario
    @Test
    public void deleteUserRemovesUserSuccessfully() throws Exception {
        // Arrange: Crear y guardar un usuario
        User user = userRepository.save(User.builder()
                .name("Pedro Martínez")
                .email("pedro@mail.com")
                .password(passwordEncoder.encode("clave456"))
                .status(UserStatus.ACTIVE)
                .city("MEDELLIN")
                .build());

        String userId = user.getId();

        // Act: Eliminar al usuario por su ID usando el servicio
        userService.deleteUser(userId);

        // Assert: Verificar que el usuario ya no existe en la base de datos
        Optional<User> deletedUser = userRepository.findById(userId);
        assertFalse(deletedUser.isPresent());
    }

    //cambiar contraseña
    @Test
    public void changePasswordThrowsExceptionIfCodeIsInvalid() {
        // Arrange
        User user = userRepository.save(User.builder()
                .name("Luisa")
                .email("luisa@mail.com")
                .password(passwordEncoder.encode("claveOriginal"))
                .validationCode(new ValidationCode("654321", LocalDateTime.now()))
                .status(UserStatus.ACTIVE)
                .build());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            userService.changePassword("luisa@mail.com", "codigoErroneo", "nuevaClave123");
        });

        assertEquals("El código ingresado no es correcto", exception.getMessage());
    }

    //verificación del código pero el correo no existe
    @Test
    public void sendVerificationCodeThrowsExceptionIfUserNotExists() {
        // Arrange
        String email = "noexiste@mail.com";

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            userService.sendVerificationCode(email);
        });

        assertEquals("No existe una cuenta asociada al correo", exception.getMessage());
    }

    //cuando sí se encuentra el usuario y se genera el código
    @Test
    public void sendVerificationCodeSuccess() throws Exception {
        // Arrange
        User user = userRepository.save(User.builder()
                .name("Camila")
                .email("camila@mail.com")
                .password(passwordEncoder.encode("clave123"))
                .status(UserStatus.INACTIVE)
                .build()
        );

        // Act
        userService.sendVerificationCode("camila@mail.com");

        // Assert
        User updatedUser = userRepository.findById(user.getId()).orElseThrow();
        assertNotNull(updatedUser.getValidationCode());
        assertNotNull(updatedUser.getValidationCode().getCode());
        assertFalse(updatedUser.getValidationCode().getCode().isEmpty());
    }



}
