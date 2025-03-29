package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.CreateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.ReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.UpdateReportDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    /*
    CRUD Reports
     */
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createReport(@Valid @RequestBody CreateReportDTO account) throws Exception{
        reportService.createReport(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su reporte se ha creado exitosamente"));
    }

    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateReport(@Valid @RequestBody UpdateReportDTO account) throws Exception{
        reportService.updateReport(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte editado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteReport(@PathVariable String id)
            throws Exception{
        reportService.deleteReport(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte eliminado exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<ReportDTO>> getReport(@PathVariable String id) throws Exception{
        ReportDTO info= reportService.getReport(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los reportes
     */
    @GetMapping
    public ResponseEntity<MessageDTO<List<ReportDTO>>> listAllReports(){
        List<ReportDTO>list= reportService.listAllReports();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //

}

