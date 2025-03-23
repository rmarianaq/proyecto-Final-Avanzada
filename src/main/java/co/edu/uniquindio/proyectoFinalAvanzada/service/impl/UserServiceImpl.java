package co.edu.uniquindio.proyectoFinalAvanzada.service.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserFilterDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //es una anotación de Spring que indica que esta clase es un servicio. Se usa en la capa de servicio para manejar la lógica de negocio.
public class UserServiceImpl implements UserService {


    @Override
    public void createUser(CreateUserDTO account) {
        System.out.println("Usuario creado: " + account.name());
    }

    @Override
    public void updateUser(UpdateUserDTO account) {
        System.out.println("Usuario actualizado: " + account.name());
    }

    @Override
    public void deleteUser(String id) {
        System.out.println("Usuario eliminado con id: " + id);
    }

    @Override
    public UserDTO getUser(String id) {
        return new UserDTO(
                id
        );
    }

    @Override
    public List<UserDTO> listAllUsers(UserFilterDTO filter) {
        return List.of(
                new UserDTO("1"),
                new UserDTO("2")
        );
    }
}
