package co.edu.uniquindio.proyectoFinalAvanzada.service.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //es una anotación de Spring que indica que esta clase es un servicio. Se usa en la capa de servicio para manejar la lógica de negocio.
public class UserServiceImpl implements UserService {


    @Override
    public void createUser(CreateUserDTO account) {

    }

    @Override
    public void updateUser(UpdateUserDTO account) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public UserDTO getUser(String id) {
        return null;
    }

    @Override
    public List<UserDTO> listAllUsers(String name, String city) {
        return null;
    }
}
