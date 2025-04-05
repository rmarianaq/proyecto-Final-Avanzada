package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.*;
import co.edu.uniquindio.proyectoFinalAvanzada.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Register user", description = "lets the user create their account")
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createUser(@Valid @RequestBody CreateUserDTO account) throws Exception{
        userService.createUser(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su registro ha sido exitoso"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update user profile", description = "lets the user update their account")
    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateUser(@Valid @RequestBody UpdateUserDTO account) throws Exception{
        userService.updateUser(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta editada exitosamente"));
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete user profile", description = "lets the user delete their account")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteUser(@PathVariable String id)
            throws Exception{
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta eliminada exitosamente"));
    }
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "details of user", description = "shows userinformation")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<UserDTO>> getUser(@PathVariable String id) throws Exception{
        UserDTO info= userService.getUser(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Users list", description = "list the users by name and city")
    @GetMapping
    public ResponseEntity<MessageDTO<List<UserDTO>>> listAllUsers(@ModelAttribute UserFilterDTO filter){
        List<UserDTO>list= userService.listAllUsers(filter);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //

    @Operation(summary = "Activated account", description = "Activate the account of new users")
    @PutMapping("/{email}/activate")
    public ResponseEntity<MessageDTO<String>> activateAccount(@PathVariable String email, @Valid @RequestBody ActivateAccountDTO account) throws Exception{
        userService.activateAccount(email, account.code());
        return ResponseEntity.ok(new MessageDTO<>(false, "Cuenta activada exitosamente"));
    }

    @Operation(summary = "password change", description = "Allows the user to change the password when they forgot it or want to change it")
    @PutMapping("/{email}/password")
    public ResponseEntity<MessageDTO<String>> changePassword(@PathVariable String email, @Valid @RequestBody ChangePasswordDTO account) throws Exception{
        userService.changePassword(email, account.code(), account.newPass());
        return ResponseEntity.ok(new MessageDTO<>(false, "Contrasenia cambiada exitosamente"));
    }

    @Operation(summary = "Verification code", description = "Allow the user request for a new verification code if they have not submitted one")
    @PostMapping("/{email}/verificationCode")
    public ResponseEntity<MessageDTO<String>> verificationCode(@PathVariable String email) throws Exception{
        userService.verificationCode(email);
        return ResponseEntity.ok(new MessageDTO<>(false, "Codigo de verificacion enviado"));
    }
}
