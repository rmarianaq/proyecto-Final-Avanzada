package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.MessageDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.NotificationService;
import co.edu.uniquindio.proyectoFinalAvanzada.service.ReportService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/{id}")
    public ResponseEntity<MessageDTO<SendNotificationDTO>> sendNotification(@PathVariable String idReport, @RequestBody SendNotificationDTO sendNotificationDTO){
        return ResponseEntity.ok(new MessageDTO<>(false, null));
    }

    @GetMapping
    public ResponseEntity<MessageDTO<List<NotificationDTO>>> listAllNotifications(){
        List<NotificationDTO>list= notificationService.listAllNotifications();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
}
