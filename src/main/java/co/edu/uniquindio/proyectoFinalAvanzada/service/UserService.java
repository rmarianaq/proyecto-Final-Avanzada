package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserFilterDTO;

import java.util.List;

public interface UserService {
    void createUser(CreateUserDTO account) throws Exception;

    void updateUser(UpdateUserDTO account) throws Exception;

    void deleteUser(String id) throws Exception;

    UserDTO getUser(String id) throws Exception;

    List<UserDTO> listAllUsers(UserFilterDTO filter);

    void changePassword(String email, String code, String s)throws Exception;

    void activateAccount(String email, String code) throws Exception;

    void verificationCode(String email) throws Exception;
}
