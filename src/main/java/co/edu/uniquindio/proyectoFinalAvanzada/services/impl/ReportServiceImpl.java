package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.ReportNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.ReportMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MongoTemplate mongoTemplate;
    private final EmailService emailService;
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
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
        return null;
    }

    @Override
    public List<ReportDTO> filterReportsLocation(LocationFilterDTO filter) {
        return null;
    }

    @Override
    public void createComment(String id, CommentDTO account) {

    }

    @Override
    public void deleteComment(String id) {

    }

    @Override
    public List<CommentDTO> listAllComments(String id) {
        return null;
    }

    @Override
    public void markAsImportant(String id) {

    }

    @Override
    public void changeStatus(String id, StatusDTO account) {

    }

    @Override
    public List<StatusDTO> listAllStatus(String id) {
        return null;
    }
}
