package co.edu.uniquindio.proyectoFinalAvanzada.services;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
