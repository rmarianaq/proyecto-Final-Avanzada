package co.edu.uniquindio.proyectoFinalAvanzada.repositories;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {

    //Busca un reporte por su categoria
    Page<Report> findByCategoryContaining(ObjectId categoryId, Pageable pageable);

    List<Report> findByUserIdOrderByDateAsc(String user);

    List<Report> findByUserIdOrderByDateDesc(String user);

    // Consulta ignorando mayúsculas/minúsculas
    Page<Report> findByTitleContainingIgnoreCase(String text, Pageable pageable);

    // Reportes que están cerca a una latitud/longitud
    @Query("{ 'location': { $near: { $geometry: { type: 'Point', coordinates: [?0, ?1] }, $maxDistance: ?2 } } }")
    List<Report> findReportsNear(double lng, double lat, double maxDistanceMeters);

    List<Report> findByStatus(String status);
}
