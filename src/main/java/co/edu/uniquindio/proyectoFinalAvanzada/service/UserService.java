package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserFilterDTO;

import java.util.List;

public interface UserService {
    void createUser(CreateUserDTO account);

    void updateUser(UpdateUserDTO account);

    void deleteUser(String id);

    UserDTO getUser(String id);

    List<UserDTO> listAllUsers(UserFilterDTO filter);
}
