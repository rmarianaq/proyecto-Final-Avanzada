package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.*;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.reports.*;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.ReportStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    private final ReportService reportService;

    /*
    CRUD Reports
     */
    @Operation(summary = "user-created report", description = "allows the user create report")
    @PostMapping
    public ResponseEntity<MessageDTO<String>> createReport(@Valid @RequestBody CreateReportDTO account) throws Exception{
        reportService.createReport(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su reporte se ha creado exitosamente"));
    }

    @Operation(summary = "user-update report", description = "allows the user update report")
    @PutMapping
    public ResponseEntity<MessageDTO<String>> updateReport(@Valid @RequestBody UpdateReportDTO account) throws Exception{
        reportService.updateReport(account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte editado exitosamente"));
    }

    @Operation(summary = "user-delete report", description = "allows the user delete report")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO<String>> deleteReport(@PathVariable String id)
            throws Exception{
        reportService.deleteReport(id);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte eliminado exitosamente"));
    }

    @Operation(summary = "get report", description = "gets reports by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO<ReportDTO>> getReport(@PathVariable String id) throws Exception{
        ReportDTO info= reportService.getReport(id);
        return ResponseEntity.ok(new MessageDTO<>(false, info));    }

    /*
    lista los reportes
     */
    @Operation(summary = "list reports", description = "list all created reports")
    @GetMapping
    public ResponseEntity<MessageDTO<List<ReportDTO>>> listAllReports(){
        List<ReportDTO>list= reportService.listAllReports();
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
    //
    /*
    Metodo para listar los reportes en un ubicacion en especifico
     */

    @Operation(summary = "filter report by location", description = "filters reports by geographic location")
    @GetMapping("/local")
    public ResponseEntity<MessageDTO<List<ReportDTO>>> filterReportsLocation(@ModelAttribute LocationFilterDTO filter){
        List<ReportDTO>list= reportService.filterReportsLocation(filter);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }

    @Operation(summary = "user-create comment", description = "allows the user create comment")
    @PostMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<String>> createComment(@PathVariable String id, @Valid @RequestBody CreateCommentDTO account) throws Exception{
        reportService.createComment(id,account);
        return ResponseEntity.ok(new MessageDTO<>(false, "Su Comentario se ha creado exitosamente"));
    }

    @Operation(summary = "user-delete comment", description = "allows the user delete comment")
    @DeleteMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<String>> deleteCommennt(@PathVariable String id, @Valid @RequestBody CommentDTO account) throws Exception{
        reportService.deleteComment(id, account.id());
        return ResponseEntity.ok(new MessageDTO<>(false, "Comentario eliminado exitosamente"));
    }

    @Operation(summary = "list all comments", description = "lists all comments that have been made")
    @GetMapping("/{id}/comment")
    public ResponseEntity<MessageDTO<List<CommentDTO>>> listAllComments(@PathVariable String id){
        List<CommentDTO>list= reportService.listAllComments(id);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }

    @Operation(summary = "mark as important", description = "allows marking reports as important")
    @PutMapping("/{id}/important")
    public ResponseEntity<MessageDTO<String>> markAsImportant(@PathVariable String id, @RequestParam String idUser) throws Exception{
        reportService.markAsImportant(id, idUser);
        return ResponseEntity.ok(new MessageDTO<>(false, "Reporte marcado como importante exitosamente"));
    }

    @Operation(summary = "change status", description = "Allows change the status of the report")
    @PostMapping("/{id}/status")
    public ResponseEntity<MessageDTO<String>> changeStatus(@PathVariable String id, @RequestParam ReportStatus newStatus) throws Exception{
        reportService.changeStatus(id, newStatus);
        return ResponseEntity.ok(new MessageDTO<>(false, "El estado ha cambiado con exito"));
    }

    @Operation(summary = "list all status", description = "lists the statuses of the reports")
    @GetMapping("/{id}/status")
    public ResponseEntity<MessageDTO<List<ReportDTO>>> listAllStatus(@PathVariable String id, @RequestParam ReportStatus status){
        List<ReportDTO>list= reportService.listAllStatus(id,status);
        return ResponseEntity.ok(new MessageDTO<>(false, list));
    }
}

