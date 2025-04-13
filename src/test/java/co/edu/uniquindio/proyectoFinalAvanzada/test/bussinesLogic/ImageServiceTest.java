package co.edu.uniquindio.proyectoFinalAvanzada.test.bussinesLogic;

import co.edu.uniquindio.proyectoFinalAvanzada.services.ImageService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.impl.ImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageServiceTest {

    @Test
    void testUpLoadImage() throws Exception {
        ImageServiceImpl imageService = new ImageServiceImpl();
        // Carga una imagen de prueba
        InputStream imageStream = getClass().getResourceAsStream("/logo.png");
        assertNotNull(imageStream, "No se encontró la imagen de prueba");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test-image.png",
                "image/png",
                imageStream
        );

        Map result = imageService.uploadImage(image);

        assertNotNull(result);
        assertTrue(result.containsKey("url"), "La respuesta debe contener el url de la imagen subida");

        System.out.println("Exito al subir la imagen " + result.get("url"));
    }

}
