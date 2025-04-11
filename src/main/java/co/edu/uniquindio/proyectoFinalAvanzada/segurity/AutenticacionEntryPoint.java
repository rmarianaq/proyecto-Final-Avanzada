package co.edu.uniquindio.proyectoFinalAvanzada.segurity;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AutenticacionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {


        MessageDTO<String> dto = new MessageDTO<>(true, "No tienes permisos para acceder a este recurso");


        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();




    }

}
