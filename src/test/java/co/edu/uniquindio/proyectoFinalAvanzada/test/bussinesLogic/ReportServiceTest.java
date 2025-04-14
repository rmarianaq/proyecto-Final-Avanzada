package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.CommentMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.ReportMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Comment;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CommentRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.NotificationService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.ReportServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ReportMapper reportMapper;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private NotificationService notificationService;

    @Test
    void testCreateReport() {
        CreateReportDTO dto = new CreateReportDTO(
                "Título de prueba",
                "Descripción del reporte",
                5.0,
                -75.0,
                List.of("imagen1.jpg", "imagen2.jpg"),
                "123",
                ReportStatus.PENDING
        );

        Report mockReport = new Report(); // objeto vacío, simula lo que devuelve el mapper

        when(reportMapper.toDocument(dto)).thenReturn(mockReport);

        reportService.createReport(dto);

        verify(reportRepository).save(mockReport);

        System.out.println(dto);


    }

    @Test
    void testUpdateReport() throws Exception {
        UpdateReportDTO dto = new UpdateReportDTO(
                new ObjectId().toString(),
                "Nuevo título",
                "Nueva descripción",
                6.0,
                -74.0,
                List.of("img.jpg"),
                "cat123",
                ReportStatus.PENDING
        );

        Report reportExistente = new Report();
        when(reportRepository.findById(dto.idReport())).thenReturn(Optional.of(reportExistente));

        reportService.updateReport(dto);

        verify(reportMapper).toDocument(dto, reportExistente);
        verify(reportRepository).save(reportExistente);
    }

    @Test
    void testDeleteReport() throws Exception {
        String reportId = "507f1f77bcf86cd799439011";
        Report report = new Report();
        report.setId(reportId);

        when(reportRepository.existsById(reportId)).thenReturn(true);
        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        reportService.deleteReport(reportId);

        // Verifica que se haya actualizado el estado del reporte
        verify(reportRepository).save(argThat(r ->
                r.getId().equals(reportId) && r.getStatus() == ReportStatus.DELETED
        ));
    }

    @Test
    void testFollowReport() throws Exception {
        String reportId = new ObjectId().toHexString();
        String userId = new ObjectId().toHexString();

        User user = new User();
        user.setFollowedReports(new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(reportRepository.existsById(reportId)).thenReturn(true);

        reportService.followReport(reportId, userId);

        assertTrue(user.getFollowedReports().contains(reportId));
        verify(userRepository).save(user);
    }

    @Test
    void testFilterReportsLocation() {
        LocationFilterDTO filter = new LocationFilterDTO(4.5, -74.1, 10.0, 0);

        Report report1 = new Report();
        Report report2 = new Report();

        when(reportRepository.findReportsNear(filter.latitude(), filter.longitude(), filter.radiusKm()))
                .thenReturn(List.of(report1, report2));

        List<ReportDTO> result = reportService.filterReportsLocation(filter);

        assertEquals(2, result.size());
        assertTrue(result.contains(report1));
        assertTrue(result.contains(report2));
    }

    @Test
    void testCreateComment() throws Exception {
        String reportId = new ObjectId().toString();

        CreateCommentDTO dto = new CreateCommentDTO(
                reportId,
                "user123",
                "Este es un comentario de prueba"
        );

        when(reportRepository.existsById(reportId)).thenReturn(true);

        Report report = new Report();
        report.setTitle("Título de Reporte");
        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        Comment comment = new Comment();
        when(commentMapper.toDocument(dto)).thenReturn(comment);

        reportService.createComment(reportId, dto);

        verify(commentRepository).save(comment);
        verify(notificationService).notifyFollowers(report, "Se ha agregado un nuevo comentario al reporte: \"Título de Reporte\".");
    }
    @Test
    void testDeleteComment() throws Exception {
        String reportId = new ObjectId().toString();
        String commentId = new ObjectId().toString();

        when(reportRepository.existsById(reportId)).thenReturn(true);

        Comment comment = new Comment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        reportService.deleteComment(reportId, commentId);

        verify(commentRepository).deleteById(comment.toString());
    }
    @Test
    void testChangeStatus() throws Exception {
        String reportId = new ObjectId().toString();
        ReportStatus newStatus = ReportStatus.PENDING;

        Report report = new Report();
        report.setStatus(ReportStatus.VERIFICATE);

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        reportService.changeStatus(reportId, newStatus);

        assertEquals(newStatus, report.getStatus());
        verify(reportRepository).save(report);
    }
}
