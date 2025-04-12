package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.LoginDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.TokenDTO;

public interface AuthenticationService {

    TokenDTO login(LoginDTO account) throws Exception;
}
