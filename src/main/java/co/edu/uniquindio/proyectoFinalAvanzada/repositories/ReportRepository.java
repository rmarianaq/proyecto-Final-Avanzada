package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends MongoRepository<Report, String> {

    // Buscar reporte por ID
    @Query("{ '_id' : ?0 }")
    Optional<Report> searchReportById(String idReport);

    // Buscar reportes por usuario
    @Query("{ 'idUser' : ?0 }")
    List<Report> searchReportByUser(String idUser);

    // Buscar reportes por estado
    @Query("{ 'status' : ?0 }")
    List<Report> searchReportByStatus(String status);
}
