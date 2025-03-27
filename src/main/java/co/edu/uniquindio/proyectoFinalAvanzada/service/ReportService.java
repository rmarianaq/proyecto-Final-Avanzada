package co.edu.uniquindio.proyectoFinalAvanzada.service;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.CreateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UpdateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.UserDTO;

import java.util.List;

public interface ReportService {
    void createReport(CreateReportDTO account);

    void updateReport(UpdateReportDTO account);

    void deleteReport(String id);

    ReportDTO getReport(String id);

    List<ReportDTO> listAllReports();
}
