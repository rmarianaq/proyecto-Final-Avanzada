package co.edu.uniquindio.proyectoFinalAvanzada.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {

    Map uploadImage(MultipartFile image) throws Exception;
    Map deleteImage(String idImage) throws Exception;

}
