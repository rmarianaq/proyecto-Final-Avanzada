package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.ReportNotFoundException;

import java.util.List;

public interface ReportService {
    void createReport(CreateReportDTO account);

    void updateReport(UpdateReportDTO account) throws Exception;

    void deleteReport(String id) throws Exception;

    ReportDTO getReport(String id) throws Exception;

    List<ReportDTO> listAllReports();

    List<ReportDTO> filterReportsLocation(LocationFilterDTO filter);

    void createComment(String id, CommentDTO account);

    void deleteComment(String id);

    List<CommentDTO> listAllComments(String id);

    void markAsImportant(String id);

    void changeStatus(String id, StatusDTO account);

    List<StatusDTO> listAllStatus(String id);
}
