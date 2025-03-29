package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserFilterDTO;

import java.util.List;

public interface UserService {
    void createUser(CreateUserDTO account);

    void updateUser(UpdateUserDTO account);

    void deleteUser(String id);

    UserDTO getUser(String id);

    List<UserDTO> listAllUsers(UserFilterDTO filter);

    void changePassword(String email, String code, String s);

    void activateAccount(String email, String code);

    void verificationCode(String email);
}
