package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UserController {

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

    @GetMapping
    public ResponseEntity<MessageDTO<List<UserDTO>>> listAllUsers(@RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) String city){
        List<UserDTO>list= userService.listAllUsers(name, city);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //

}
