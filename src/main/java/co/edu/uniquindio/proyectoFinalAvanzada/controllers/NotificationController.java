package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.UpdateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.NotificationService;
import co.edu.uniquindio.proyectoFinalAvanzada.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@SecurityRequirement(name = "bearerAuth")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "send notification", description = "allows sending notifications")
    @PostMapping("/{id}/send")
    public ResponseEntity<MessageDTO<String>> sendNotification(@PathVariable String id, @RequestBody SendNotificationDTO account){
        notificationService.sendNotification(id, account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Notificación enviada con éxito"));
    }

    @Operation(summary = "list notifications", description = "lists all notifications")
    @GetMapping
    public ResponseEntity<MessageDTO<List<NotificationDTO>>> listAllNotifications(){
        List<NotificationDTO>list= notificationService.listAllNotifications();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }

    @Operation(summary = "mark as read", description = "marks the notifications as read")
    @PutMapping("/{id}/read")
    public ResponseEntity<MessageDTO<String>> markAsRead(@RequestBody String id, @Valid @RequestBody NotificationUpdateDTO account) throws Exception{
        notificationService.markAsRead(id, account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Notificacion actualizada como leida"));
    }
}
