package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

public class NotificationController {

    @PostMapping("/{idReport}")
    public ResponseEntity<MessageDTO<SendNotificationDTO>> sendNotification(@PathVariable String idReport, @RequestBody SendNotificationDTO sendNotificationDTO){
        return ResponseEntity.ok(new MessageDTO<>(false, null));
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> listResponseEntity(){
        return ResponseEntity.ok(new ArrayList<NotificationDTO>());
    }
}
