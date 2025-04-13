package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.ReportNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.UserNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.CommentMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.ReportMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Comment;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CommentRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MongoTemplate mongoTemplate;
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final NotificationServiceImpl notificationService;
    @Override
    public void createReport(CreateReportDTO account) {
        Report report = reportMapper.toDocument(account);
        reportRepository.save(report);

    }

    @Override
    public void updateReport(UpdateReportDTO account) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(account.idReport())) {
            throw new ReportNotFoundException("No se encontró el reporte con el id "+account.idReport());
        }

        //Buscamos el reporte que se quiere actualizar
        ObjectId objectId = new ObjectId(account.idReport());
        Optional<Report> reportOptional = reportRepository.findById(String.valueOf(objectId));


        //Si no se encontró el reporte, lanzamos una excepción
        if(reportOptional.isEmpty()){
            throw new ReportNotFoundException("No se encontró el reporte con el id "+account.idReport());
        }

        // Mapear los datos actualizados al reporte existente
        Report report = reportOptional.get();
        reportMapper.toDocument(account, report);


        //Como el objeto reporte ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        reportRepository.save(report);

    }

    @Override
    public void deleteReport(String id) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new ReportNotFoundException("No se encontró el reporte con el id "+id);
        }

        //Buscamos el reporte que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Report> reportOptional = reportRepository.findById(String.valueOf(objectId));

        //Si no se encontró el reporte, lanzamos una excepción
        if(reportOptional.isEmpty()){
            throw new ReportNotFoundException("No se encontró el usuario con el id "+id);
        }

        //Obtenemos el reporte que se quiere eliminar y le asignamos el estado eliminado
        Report report= reportOptional.get();
        report.setStatus(ReportStatus.DELETED);

        //Como el objeto reporte ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        reportRepository.save(report);

    }

    @Override
    public ReportDTO getReport(String id) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new ReportNotFoundException("No se encontró el reporte con el id "+id);
        }

        //Buscamos el reporte que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<Report> reportOptional = reportRepository.findById(String.valueOf(objectId));

        //Si no se encontró el reporte, lanzamos una excepción
        if(reportOptional.isEmpty()){
            throw new ReportNotFoundException("No se encontró el reporte con el id "+id);
        }

        //Retornamos el reporte encontrado convertido a DTO
        return reportMapper.toDTO(reportOptional.get());
    }

    @Override
    public List<ReportDTO> listAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(reportMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReportDTO> filterReportsLocation(LocationFilterDTO filter) {
        // Validar que la página no sea menor a 0
        if (filter.page() < 0) {
            throw new RuntimeException("La página no puede ser menor a 0");
        }

        // Validar que latitud, longitud y radio no sean nulos
        if (filter.latitude() == null || filter.longitude() == null || filter.radiusKm() == null) {
            throw new RuntimeException("Debes proporcionar latitud, longitud y radio");
        }

        // Convertir el radio de kilómetros a radianes (Mongo usa radianes en $centerSphere)
        double radiusInRadians = filter.radiusKm() / 6378.1; // Radio aproximado de la Tierra en km

        // Construir el criterio geoespacial
        Criteria criteria = Criteria.where("location").withinSphere(
                new Circle(filter.longitude(), filter.latitude(), radiusInRadians)
        );

        // Crear la consulta con paginación de 5 elementos por página
        Query query = new Query(criteria).with(PageRequest.of(filter.page(), 5));

        // Ejecutar la consulta
        List<Report> reports = mongoTemplate.find(query, Report.class);

        // Convertir a DTO
        return reports.stream()
                .map(reportMapper::toDTO)
                .toList();
    }

    @Override
    public void createComment(String id, CreateCommentDTO account) throws Exception{
        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Reporte no encontrado con id: " + id);
        }
        Comment comment= commentMapper.toDocument(account);
        commentRepository.save(comment);

        Report report = reportRepository.findById(id).orElseThrow(() ->
                new ReportNotFoundException("Reporte no encontrado con id: " + id));

        // Crear mensaje personalizado
        String message = "Se ha agregado un nuevo comentario al reporte: \"" + report.getTitle() + "\".";

        // Notificar a los seguidores por correo
        notificationService.notifyFollowers(report, message);



    }

    @Override
    public void deleteComment(String id, String idComment) throws Exception {
        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Reporte no encontrado con id: " + id);
        }
        if (!ObjectId.isValid(idComment)) {
            throw new ReportNotFoundException("No se encontró el comentario con el id "+id);
        }

        //Buscamos el comentario que se quiere obtener
        ObjectId objectId = new ObjectId(idComment);
        Optional<Comment> commentOptional = commentRepository.findById(String.valueOf(objectId));

        //Si no se encontró el comentario, lanzamos una excepción
        if(commentOptional.isEmpty()){
            throw new ReportNotFoundException("No se encontró el comentario con el id "+id);
        }

        Comment comment= commentOptional.get();
        commentRepository.deleteById(String.valueOf(comment));

    }

    @Override
    public List<CommentDTO> listAllComments(String id) {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDTO)
                .toList();
    }

    @Override
    public void markAsImportant(String id, String idUser) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un reporte con el id: " + id));

        if (report.getUsersWhoMarkedImportant().contains(idUser)) {
            throw new RuntimeException("El usuario ya ha marcado este reporte como importante");
        }

        report.setImportant(report.getImportant() + 1);
        report.getUsersWhoMarkedImportant().add(idUser);

        reportRepository.save(report);

    }

    @Override
    public void changeStatus(String id, ReportStatus newStatus) throws Exception {
        Optional<Report> reportOptional = reportRepository.findById(id);

        if(reportOptional.isEmpty()){
            throw new ReportNotFoundException("El reporte no existe");
        }
        Report report = reportOptional.get();

        report.setStatus(newStatus);
        reportRepository.save(report);

        notificationService.notifyFollowers(report, "El estado del reporte ha cambiado a: " + report.getStatus());


    }

    @Override
    public List<ReportDTO> listAllStatus(String id,ReportStatus status) {
        List<Report> reports = reportRepository.findByStatus(status);

        return reports.stream()
                .map(reportMapper::toDTO)
                .toList();
    }

    @Override
    public void followReport(String id, String userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Reporte no encontrado");
        }

        if (!user.getFollowedReports().contains(id)) {
            user.getFollowedReports().add(id);
            userRepository.save(user);
        }
    }

    @Override
    public void unfollowReport(String id, String userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException("Reporte no encontrado");
        }
        if (!user.getFollowedReports().contains(id)) {
            throw new UserNotFoundException("El reporte no esta en la lista de followers");
        }
        user.getFollowedReports().remove(id);
        userRepository.save(user);
    }

    @Override
    public List<ReportDTO> getFollowedReports(String userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        List<Report> reports = reportRepository.findAllById(user.getFollowedReports());
        return reportRepository.findAll()
                .stream()
                .map(reportMapper::toDTO)
                .toList();
    }
}
