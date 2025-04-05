package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.*;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
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

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateUser(@Valid @RequestBody UpdateUserDTO account) throws Exception{
        userService.updateUser(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta editada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteUser(@PathVariable String id)
            throws Exception{
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta eliminada exitosamente"));
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<UserDTO>> getUser(@PathVariable String id) throws Exception{
        UserDTO info= userService.getUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los usuarios por su nombre y ciudad
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<MessageDTO<List<UserDTO>>> listAllUsers(@ModelAttribute UserFilterDTO filter){
        List<UserDTO>list= userService.listAllUsers(filter);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //

    @PutMapping("/{email}/activate")
    public ResponseEntity<MessageDTO<String>> activateAccount(@PathVariable String email, @Valid @RequestBody ActivateAccountDTO account) throws Exception{
        userService.activateAccount(email, account.code());
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta activada exitosamente"));
    }
    @PutMapping("/{email}/password")
    public ResponseEntity<MessageDTO<String>> changePassword(@PathVariable String email, @Valid @RequestBody ChangePasswordDTO account) throws Exception{
        userService.changePassword(email, account.code(), account.newPass());
        return ResponseEntity.ok(new MessageDTO<>(false, "Contrasenia cambiada exitosamente"));
    }
    @PostMapping("/{email}/verificationCode")
    public ResponseEntity<MessageDTO<String>> verificationCode(@PathVariable String email) throws Exception{
        userService.verificationCode(email);
        return ResponseEntity.ok(new MessageDTO<>(false, "Codigo de verificacion enviado"));
    }
}
