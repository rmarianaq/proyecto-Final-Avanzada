package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;

import java.util.List;

public interface ReportService {
    void createReport(CreateReportDTO account);

    void updateReport(UpdateReportDTO account);

    void deleteReport(String id);

    ReportDTO getReport(String id);

    List<ReportDTO> listAllReports();

    List<ReportDTO> filterReportsLocation(LocationFilterDTO filter);

    void createComment(String id, CommentDTO account);

    void deleteComment(String id);

    List<CommentDTO> listAllComments(String id);

    void markAsImportant(String id);

    void changeStatus(String id, StatusDTO account);

    List<StatusDTO> listAllStatus(String id);
}
