package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
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
    /*
    Metodo para listar los reportes en un ubicacion en especifico
     */
    @GetMapping("/local")
    public ResponseEntity<MessageDTO<List<ReportDTO>>> filterReportsLocation(@ModelAttribute LocationFilterDTO filter){
        List<ReportDTO>list= reportService.filterReportsLocation(filter);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<String>> createComment(@PathVariable String id, @Valid @RequestBody CommentDTO account) throws Exception{
        reportService.createComment(id,account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su Comentario se ha creado exitosamente"));
    }
    @DeleteMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<String>> deleteCommennt(@PathVariable String id) throws Exception{
        reportService.deleteComment(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Comentario eliminado exitosamente"));
    }
    @GetMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<List<CommentDTO>>> listAllComments(@PathVariable String id){
        List<CommentDTO>list= reportService.listAllComments(id);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    @PutMapping("/{id}/important")
    public ResponseEntity<MessageDTO<String>> markAsImportant(@PathVariable String id) throws Exception{
        reportService.markAsImportant(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte marcado como importante exitosamente"));
    }
    @PostMapping("/{id}/status")
    public ResponseEntity<MessageDTO<String>> changeStatus(@PathVariable String id, @Valid @RequestBody StatusDTO account) throws Exception{
        reportService.changeStatus(id,account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su Comentario se ha creado exitosamente"));
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<MessageDTO<List<StatusDTO>>> listAllStatus(@PathVariable String id){
        List<StatusDTO>list= reportService.listAllStatus(id);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
}

