package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class    UserController {

    private final UserService userService;

    /*
    CRUD Users
     */
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createUser(@Valid @RequestBody CreateUserDTO account) throws Exception{
        userService.createUser(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su registro ha sido exitoso"));
    }

    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateUser(@Valid @RequestBody UpdateUserDTO account) throws Exception{
        userService.updateUser(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta editada exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteUser(@PathVariable String id)
            throws Exception{
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<UserDTO>> getUser(@PathVariable String id) throws Exception{
        UserDTO info= userService.getUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los usuarios por su nombre y ciudad
     */
    @GetMapping
    public ResponseEntity<MessageDTO<List<UserDTO>>> listAllUsers(@ModelAttribute UserFilterDTO filter){
        List<UserDTO>list= userService.listAllUsers(filter);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //

}
