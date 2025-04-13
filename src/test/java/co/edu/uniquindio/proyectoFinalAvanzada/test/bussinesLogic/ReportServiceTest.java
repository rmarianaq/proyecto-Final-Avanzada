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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
//    @Mock
//    private MongoTemplate mongoTemplate;
//
//    @Mock
//    private ReportRepository reportRepository;
//
//    @Mock
//    private ReportMapper reportMapper;
//
//    @Mock
//    private CommentMapper commentMapper;
//
//    @Mock
//    private CommentRepository commentRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private NotificationService notificationService;
//
//    @InjectMocks
//    private ReportServiceImpl reportService;
//
//    @Test
//    void testCreateReport_success() {
//        CreateReportDTO dto = new CreateReportDTO("Título", "Descripción", 0.0, 0.0, "user123", "category123");
//        Report report = new Report();
//        Mockito.when(reportMapper.toDocument(dto)).thenReturn(report);
//
//        reportService.createReport(dto);
//
//        Mockito.verify(reportRepository).save(report);
//    }
//
//    @Test
//    void testUpdateReport_success() throws Exception {
//        String id = new ObjectId().toString();
//        UpdateReportDTO dto = new UpdateReportDTO(id, "Nuevo título", "Nueva desc", 15,20,"img.png");
//        Report existing = new Report();
//        existing.setId(id);
//
//        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.of(existing));
//
//        reportService.updateReport(dto);
//
//        Mockito.verify(reportMapper).toDocument(dto, existing);
//        Mockito.verify(reportRepository).save(existing);
//    }
//
//    @Test
//    void testDeleteReport_success() throws Exception {
//        String id = new ObjectId().toString();
//        Report report = new Report();
//        report.setId(id);
//
//        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.of(report));
//
//        reportService.deleteReport(id);
//
//        Assertions.assertEquals(ReportStatus.DELETED, report.getStatus());
//        Mockito.verify(reportRepository).save(report);
//    }
//
//    @Test
//    void testFollowReport_success() throws Exception {
//        String userId = "user123";
//        String reportId = "report123";
//
//        User user = new User();
//        user.setId(userId);
//        user.setFollowedReports(new ArrayList<>());
//
//        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        Mockito.when(reportRepository.existsById(reportId)).thenReturn(true);
//
//        reportService.followReport(reportId, userId);
//
//        Assertions.assertTrue(user.getFollowedReports().contains(reportId));
//        Mockito.verify(userRepository).save(user);
//    }
//
//    @Test
//    void testFilterReportsLocation_success() {
//        LocationFilterDTO filter = new LocationFilterDTO(1.0, 2.0, 3.0, 0);
//
//        Report report = new Report();
//        report.setTitle("Prueba");
//
//        List<Report> foundReports = List.of(report);
//
//        Mockito.when(mongoTemplate.find(Mockito.any(Query.class), Mockito.eq(Report.class)))
//                .thenReturn(foundReports);
//
//        Mockito.when(reportMapper.toDTO(Mockito.any(Report.class)))
//                .thenReturn(new ReportDTO("id123", "Título", "Desc", null, 0, 0, "cat", null, null, null, 0, null));
//
//        List<ReportDTO> result = reportService.filterReportsLocation(filter);
//
//        Assertions.assertEquals(1, result.size());
//    }
//
//    @Test
//    void testCreateComment_success() throws Exception {
//        String reportId = "report123";
//        CreateCommentDTO dto = new CreateCommentDTO("Texto", "user123");
//
//        Comment comment = new Comment();
//        comment.setContent("Texto");
//
//        Report report = new Report();
//        report.setId(reportId);
//        report.setTitle("Reporte 1");
//
//        Mockito.when(reportRepository.existsById(reportId)).thenReturn(true);
//        Mockito.when(commentMapper.toDocument(dto)).thenReturn(comment);
//        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));
//
//        reportService.createComment(reportId, dto);
//
//        Mockito.verify(commentRepository).save(comment);
//        Mockito.verify(notificationService).notifyFollowers(report, "Se ha agregado un nuevo comentario al reporte: \"Reporte 1\".");
//    }
}
