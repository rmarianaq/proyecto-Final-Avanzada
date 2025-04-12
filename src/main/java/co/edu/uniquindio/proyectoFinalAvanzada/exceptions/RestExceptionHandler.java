package co.edu.uniquindio.proyectoFinalAvanzada.exceptions;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.ValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.ArrayList;
import java.util.List;

/*
Mostrará un mensaje de error personalizado al usuario según el tipo de excepción que arroje el servicio (cualquier servicio).
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /*
    Excepción que se lanza cuando se trata de acceder a un recurso que no existe.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MessageDTO<String>> noResourceFoundExceptionHandler (NoResourceFoundException ex){
        return ResponseEntity.status(404).body( new MessageDTO<>(true, "El recurso no fue encontrado") );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageDTO<String>> userNotFoundExceptionHandler (UserNotFoundException ex){
        return ResponseEntity.status(404).body( new MessageDTO<>(true, ex.getMessage()) );
    }
    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<MessageDTO<String>> reportNotFoundExceptionHandler (ReportNotFoundException ex){
        return ResponseEntity.status(404).body( new MessageDTO<>(true, ex.getMessage()) );
    }


    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<MessageDTO<String>> userNotFoundExceptionHandler (EmailNotValidException ex){
        return ResponseEntity.status(409).body( new MessageDTO<>(true, ex.getMessage()) );
    }

    /*
    Excepción general de Java. Lo ideal es tener excepciones propias para cada tipo de error y no ponerle a todas las excepciones simplemente Exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO<String>> generalExceptionHandler (Exception e){
        return ResponseEntity.internalServerError().body( new MessageDTO<>(true, e.getMessage()) );
    }

    /*
    Excepción que se lanza cuando no se cumple alguna validación puesta en los DTO (@NotNull, @Email, @Max, etc..).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO<List<ValidationDTO>>> validationExceptionHandler ( MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();


        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidationDTO(e.getField(), e.getDefaultMessage()) );
        }


        return ResponseEntity.badRequest().body( new MessageDTO<>(true, errores) );
    }


}
