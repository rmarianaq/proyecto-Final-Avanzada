package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.CommentMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.ReportMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Comment;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.Municipality;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.Location;
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

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    //
    //MockMVC
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
                new Location(5.0, -75.0),
                List.of("imagen1.jpg", "imagen2.jpg"),
                List.of("123"),
                ReportStatus.PENDING,
                "idUsuario",
                Municipality.ARMENIA
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
                new Location(5.0, -75.0),
                List.of("imagen1.jpg", "imagen2.jpg"),
                List.of("123"),
                ReportStatus.PENDING,
                Municipality.ARMENIA
        );

        Report reportExistente = new Report();
        when(reportRepository.findById(dto.id())).thenReturn(Optional.of(reportExistente));

        reportService.updateReport(dto);

        verify(reportMapper).toDocument(dto, reportExistente);
        verify(reportRepository).save(reportExistente);
    }

    @Test
    void testDeleteReport() throws Exception {
        String reportId = "67f73fd5f8dcee3803870a2a";
        Report report = new Report();
        report.setId(reportId);

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        reportService.deleteReport(reportId);

        // Verifica que se haya actualizado el estado del reporte
        verify(reportRepository).save(argThat(r ->
                r.getId().equals(reportId) && r.getStatus() == ReportStatus.DELETED
        ));
    }

    @Test
    void testGetReport() throws Exception {
        // Arrange
        String reportId = new ObjectId().toHexString(); // ID válido
        Report report = new Report();
        report.setId(reportId);

        ReportDTO expectedDTO = new ReportDTO(
                reportId,
                "Título de prueba",
                "Descripción",
                new Location(4.5, -75.7),
                List.of("img1.jpg"),
                List.of("cat1"),
                ReportStatus.PENDING,
                LocalDateTime.now()
        );

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));
        when(reportMapper.toDTO(report)).thenReturn(expectedDTO);

        // Act
        ReportDTO result = reportService.getReport(reportId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);
    }

    @Test
    void testListAllReports() {
        // Arrange
        Report report1 = new Report();
        report1.setId("r1");
        Report report2 = new Report();
        report2.setId("r2");

        ReportDTO dto1 = new ReportDTO("r1", "Título 1", "Desc", new Location(1.5,2.55), List.of(), List.of(), ReportStatus.PENDING, LocalDateTime.now());
        ReportDTO dto2 = new ReportDTO("r2", "Título 2", "Desc", new Location(3.3,4.1), List.of(), List.of(), ReportStatus.PENDING, LocalDateTime.now());

        when(reportRepository.findAll()).thenReturn(List.of(report1, report2));
        when(reportMapper.toDTO(report1)).thenReturn(dto1);
        when(reportMapper.toDTO(report2)).thenReturn(dto2);

        // Act
        List<ReportDTO> result = reportService.listAllReports();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
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
        LocationFilterDTO filter = new LocationFilterDTO(new Location(4.5,-5.8), 74.1, 10);

        Report report1 = new Report();
        report1.setId("r1");
        Report report2 = new Report();
        report1.setId("r2");

        when(mongoTemplate.find(any(Query.class), eq(Report.class)))
                .thenReturn(List.of(report1, report2));

        ReportDTO dto1 = new ReportDTO("r1", "pruebla","desc prueba", new Location(4.1,5.2), List.of("imagen1.jpg", "imagen2.jpg"),
                List.of("123"), ReportStatus.VERIFICATE, LocalDateTime.now());
        ReportDTO dto2 = new ReportDTO("r2", "pruebla","desc prueba", new Location(4.1,5.2), List.of("imagen1.jpg", "imagen2.jpg"),
                List.of("123"), ReportStatus.VERIFICATE, LocalDateTime.now());

        when(reportMapper.toDTO(report1)).thenReturn(dto1);
        when(reportMapper.toDTO(report2)).thenReturn(dto2);


        List<ReportDTO> result = reportService.filterReportsLocation(filter);

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
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
    void testListAllComments() {
        // Arrange
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();

        ObjectId reportId = new ObjectId();
        ObjectId userId1 = new ObjectId();
        ObjectId userId2 = new ObjectId();

        comment1.setIdReport(reportId);
        comment1.setIdUser(userId1);

        comment2.setIdReport(reportId);
        comment2.setIdUser(userId2);

        List<Comment> commentList = List.of(comment1, comment2);

        CommentDTO dto1 = new CommentDTO("c1", reportId.toString(), userId1.toString(), "comentario", LocalDateTime.now());
        CommentDTO dto2 = new CommentDTO("c2", reportId.toString(), userId2.toString(), "comment 2", LocalDateTime.now());

        when(commentRepository.findAll()).thenReturn(commentList);
        when(commentMapper.toDTO(comment1)).thenReturn(dto1);
        when(commentMapper.toDTO(comment2)).thenReturn(dto2);

        // Act
        List<CommentDTO> result = reportService.listAllComments(reportId.toString());

        // Assert
        assertEquals(2, result.size());
        assertEquals("c1", result.get(0).id()); // si usas record
        assertEquals("c2", result.get(1).id());
    }
    @Test
    void testMarkAsImportant() {
        // Arrange
        String reportId = "r1";
        String userId = "user123";

        Report report = new Report();
        report.setId(reportId);
        report.setImportant(0);
        report.setUsersWhoMarkedImportant(new HashSet<>());

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        // Act
        reportService.markAsImportant(reportId, userId);

        // Assert
        assertEquals(1, report.getImportant());
        assertTrue(report.getUsersWhoMarkedImportant().contains(userId));

        verify(reportRepository).save(report);
    }
    @Test
    void testMarkAsImportant_UserAlreadyMarked() {
        // Arrange
        String reportId = "r1";
        String userId = "user123";

        Report report = new Report();
        report.setId(reportId);
        report.setImportant(1);
        report.setUsersWhoMarkedImportant(new HashSet<>(Set.of(userId)));

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reportService.markAsImportant(reportId, userId);
        });

        assertEquals("El usuario ya ha marcado este reporte como importante", exception.getMessage());
        verify(reportRepository, never()).save(any());
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
