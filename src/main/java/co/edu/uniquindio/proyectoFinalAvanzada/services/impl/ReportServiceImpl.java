package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.services.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public void createReport(CreateReportDTO account) {

    }

    @Override
    public void updateReport(UpdateReportDTO account) {

    }

    @Override
    public void deleteReport(String id) {

    }

    @Override
    public ReportDTO getReport(String id) {
        return null;
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
