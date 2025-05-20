package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;

import java.util.List;

public interface ReportService {
    void createReport(CreateReportDTO account);

    void updateReport(UpdateReportDTO account) throws Exception;

    void deleteReport(String id) throws Exception;

    ReportDTO getReport(String id) throws Exception;

    List<ReportDTO> listAllReports();

    List<ReportDTO> filterReportsLocation(LocationFilterDTO filter);

    void createComment(String id, CreateCommentDTO account)throws Exception;

    void deleteComment(String id, String idComment) throws Exception;

    List<CommentDTO> listAllComments(String id);

    void markAsImportant(String id, String idUser);

    void changeStatus(String id, ReportStatus newStatus) throws Exception;

    List<ReportDTO> listAllStatus(String id,ReportStatus status);

    void followReport(String id, String idUser) throws Exception;

    void unfollowReport(String id, String idUser)throws Exception;

    List<ReportDTO> getFollowedReports(String idUser) throws Exception;
}
