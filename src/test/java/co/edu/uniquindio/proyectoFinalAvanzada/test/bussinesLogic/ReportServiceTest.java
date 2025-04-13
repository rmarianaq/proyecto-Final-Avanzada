package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.repositories.ReportRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportServiceTest {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportServiceImpl reportService;
}
