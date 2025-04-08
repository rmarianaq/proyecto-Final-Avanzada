package co.edu.uniquindio.proyectoFinalAvanzada.test;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ReportTest {
    @Autowired //para que sea inicializada automáticamente por Spring.
    private ReportRepository reportRepository;

    @Test
    public void createTest() {
        List<ObjectId> categories = List.of(
                new ObjectId("67f58810cbfef4113e8268de"),
                new ObjectId("67f5886fd6eb5453d92ef096")
        );
        //Creamos un reporte con sus propiedades
        Report report = Report.builder()
                .title("Incendio en refugio canino")
                .category(categories)
                .city("Armenia")
                .description("En las horas de la tarde se presento un fuerte incendio en el refugio patitas pet")
                .date(new Date())
                .pictures(List.of("foto1.png", "foto2.png"))
                .build();


        //Guardamos el reporte en la base de datos
        Report savedReport = reportRepository.save(report);

        //Verificamos que se haya guardado validando que no sea null
        assertNotNull(savedReport);
    }

    @Test
    public void updateTest(){
        //Definimos el id del reporte (de MongoDB)
        ObjectId id = new ObjectId("67f589373a245825d49340ed");

        //Obtenemos el reporte con el id XXXXXXX
        Report report = reportRepository.findById(String.valueOf(id)).orElseThrow();

        //Modificar el titulo del reporte
        report.setTitle("Tragedia en refugio canino");

        //Guardamos nuevamente el reporte
        reportRepository.save( report );

        //Obtenemos el reporte con el id XXXXXXX nuevamente
        Report updatedReport = reportRepository.findById(String.valueOf(id)).orElseThrow();;

        //Verificamos que el tirulo se haya actualizado
        assertEquals("Tragedia en refugio canino", updatedReport.getTitle());
    }

    @Test
    public void listAllTest(){
        //Obtenemos la lista de todos los reportes (por ahora solo tenemos 1)
        List<Report> list = reportRepository.findAll();

        //Imprimimos los reportes, se hace uso de una función lambda
        list.forEach(System.out::println);

        //Verificamos que la lista no esta vacia
        assertTrue(list.size() >= 1);;
    }
    @Test
    public void deleteTest(){
        //Definimos el id del reporte que queremos borrar
        ObjectId id = new ObjectId("67f589373a245825d49340ed");

        //Borramos el reporte con el id XXXXXXX
        reportRepository.deleteById(String.valueOf(id));

        //Obtenemos el reporte con el id XXXXXXX
        Report report = reportRepository.findById(String.valueOf(id)).orElse(null);

        //Verificamos que el reporte no exista, debe ser null ya que fue eliminado
        assertNull(report);
    }
}
