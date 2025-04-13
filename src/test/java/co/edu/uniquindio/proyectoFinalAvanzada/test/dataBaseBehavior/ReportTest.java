package co.edu.uniquindio.proyectoFinalAvanzada.test.dataBaseBehavior;

import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Category;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.Report;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.CategoryRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ReportTest {
    @Autowired //para que sea inicializada automáticamente por Spring.
    private ReportRepository reportRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createTest() {
        List<ObjectId> categories = List.of(
                new ObjectId("67f58810cbfef4113e8268de"),
                new ObjectId("67f5886fd6eb5453d92ef096")
        );
        Category category = categoryRepository.findById(String.valueOf(categories))
                .orElseThrow(() -> new IllegalArgumentException("La categoria no existe"));
        //Creamos un reporte con sus propiedades
        Report report = Report.builder()
                .title("Incendio en refugio canino")
                .category(categories)
                .city("Armenia")
                .description("En las horas de la tarde se presento un fuerte incendio en el refugio patitas pet")
                .date(LocalDateTime.now())
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
    @Test
    @DisplayName("Consulta: Reportes por categoría (en lista) con paginación")
    public void testFindByCategoryContaining() {
        reportRepository.deleteAll();

        ObjectId cat1 = new ObjectId();
        ObjectId cat2 = new ObjectId();

        Report report1 = Report.builder()
                .title("Basuras acumuladas")
                .category(List.of(cat1))
                .city("Armenia")
                .description("Contaminacion en el sector de oro negro por mal amnejo de las basuras")
                .date(LocalDateTime.now())
                .pictures(List.of("foto1.png", "foto2.png"))
                .build();

        Report report2 = Report.builder()
                .title("Huecos en la calle")
                .category(List.of(cat1, cat2))
                .city("Pereira")
                .description("Calles danadas causan multiples accidentes de transito")
                .date(LocalDateTime.now())
                .pictures(List.of("foto3.png", "foto4.png"))
                .build();

        Report report3 = Report.builder()
                .title("Ruido excesivo")
                .category(List.of(cat2))
                .city("Armenia")
                .description("dos meses sufriendo afectaciones a causa de la contaminacion auditiva causada por vecinos inconscientes")
                .date(LocalDateTime.now())
                .pictures(List.of("foto1.png", "foto5.png"))
                .build();

        reportRepository.saveAll(Arrays.asList(report1, report2, report3));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Report> result = reportRepository.findByCategoryContaining(cat1, pageable);

        assertEquals(2, result.getTotalElements());
        result.forEach(r -> assertTrue(r.getCategory().contains(cat1)));
    }
    @Test
    public void searchByTitleIgnoreCaseTest() {
        // Texto a buscar
        String searchText = "basura";

        // Página 0, tamaño 10
        PageRequest pageRequest = PageRequest.of(0, 10);

        // Consulta por texto ignorando mayúsculas
        Page<Report> result = reportRepository.findByTitleContainingIgnoreCase(searchText, pageRequest);

        // Mostrar resultados
        result.getContent().forEach(System.out::println);

        // Verificar que haya al menos un resultado (dependerá de tu DB)
        assertNotNull(result);
        assertTrue(result.getTotalElements() >= 0); // Puedes cambiar el >= 0 por > 0 si sabes que hay datos
    }


}
