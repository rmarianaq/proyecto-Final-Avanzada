package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CreateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.UpdateReportDTO;

import java.util.List;

public interface ReportService {
    void createReport(CreateReportDTO account);

    void updateReport(UpdateReportDTO account);

    void deleteReport(String id);

    ReportDTO getReport(String id);

    List<ReportDTO> listAllReports();
}
