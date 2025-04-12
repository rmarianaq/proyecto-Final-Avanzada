package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.LoginDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.TokenDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.segurity.JWTUtils;
import co.edu.uniquindio.proyectoFinalAvanzada.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO account) throws Exception{

        Optional<User> userOptional = userRepository.findByEmail(account.email());


        if(userOptional.isEmpty()){
            throw new Exception("El usuario no existe");
        }


        User user = userOptional.get();


        // Verificar si la contraseña es correcta usando el PasswordEncoder
        if(!passwordEncoder.matches(account.password(), user.getPassword())){
            throw new Exception("El usuario no existe");
        }


        String token = jwtUtils.generateToken(user.getId().toString(), crearClaims(user));
        return new TokenDTO(token);
    }


    private Map<String, String> crearClaims(User user){
        return Map.of(
                "email", user.getEmail(),
                "nombre", user.getName(),
                "rol", "ROLE_"+user.getRol().name()
        );

    }
}
