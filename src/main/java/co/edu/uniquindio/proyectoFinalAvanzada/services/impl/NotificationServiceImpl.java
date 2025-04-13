package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.NotificationUpdateDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.notification.SendNotificationDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.NotificationMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Notification;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.NotificationRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Override
    public List<NotificationDTO> listAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDTO)
                .toList();
    }

    @Override
    public void markAsRead(String id, NotificationUpdateDTO account) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe una notificación con id: " + id));

        notification.setRead(account.read());
        notificationRepository.save(notification);
    }

    @Override
    public void sendNotification(String id, SendNotificationDTO account) {

        Notification notification = notificationMapper.toDocument(account);
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    @Override
    public void notifyFollowers(Report report, String message) throws Exception {
        List<User> followers = userRepository.findByFollowedReportsContains(report.getId());

        for (User follower : followers) {
            String subject = "Actualización de reporte: " + report.getTitle();
            String body = "Hola " + follower.getName() + ",\n\n"
                    + message + "\n\n"
                    + "Revisa el reporte en la plataforma.\n\nGracias.";

            EmailDTO emailDTO = new EmailDTO(subject, body, follower.getEmail());
            emailService.sendEmail(emailDTO);
        }
    }
}
