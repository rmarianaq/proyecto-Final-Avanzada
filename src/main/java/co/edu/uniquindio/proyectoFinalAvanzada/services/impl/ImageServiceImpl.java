package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class ImageServiceImpl implements ImageService {
    private final Cloudinary cloudinary;

    public ImageServiceImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dhesl2p39");
        config.put("api_key", "634819465815177");
        config.put("api_secret", "_YfDROp3oRBKpw4QmtQNndrV1wg");


        cloudinary = new Cloudinary(config);
    }



    @Override
    public Map uploadImage(MultipartFile image) throws Exception {
        File file = convert(image);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "reports"));
    }

    @Override
    public Map deleteImage(String idImage) throws Exception {
        return cloudinary.uploader().destroy(idImage, ObjectUtils.emptyMap());

    }
    private File convert(MultipartFile image) throws IOException {
        File file = File.createTempFile(image.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(image.getBytes());
        fos.close();
        return file;
    }

}
