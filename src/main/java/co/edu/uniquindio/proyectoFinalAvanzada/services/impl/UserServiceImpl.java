package co.edu.uniquindio.proyectoFinalAvanzada.services.impl;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.EmailDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserFilterDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.EmailNotValidException;
import co.edu.uniquindio.proyectoFinalAvanzada.exceptions.UserNotFoundException;
import co.edu.uniquindio.proyectoFinalAvanzada.mapper.UserMapper;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import co.edu.uniquindio.proyectoFinalAvanzada.model.enums.UserStatus;
import co.edu.uniquindio.proyectoFinalAvanzada.model.vo.ValidationCode;
import co.edu.uniquindio.proyectoFinalAvanzada.repositories.UserRepository;
import co.edu.uniquindio.proyectoFinalAvanzada.services.EmailService;
import co.edu.uniquindio.proyectoFinalAvanzada.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service //es una anotación de Spring que indica que esta clase es un servicio. Se usa en la capa de servicio para manejar la lógica de negocio.
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MongoTemplate mongoTemplate;
    private final EmailService emailService;


    @Override
    public void createUser(CreateUserDTO account) throws Exception {
        User user = userMapper.toDocument(account);
        if( emailExist(account.email()) ){
            throw new EmailNotValidException("El correo "+account.email()+" ya está en uso");
        }

        userRepository.save(user);
        //Enviar un email con el codigo de activacion
        sendVerificationCode(account.email());
    }
    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void updateUser(UpdateUserDTO account) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(account.idUser())) {
            throw new UserNotFoundException("No se encontró el usuario con el id "+account.idUser());
        }

        //Buscamos el usuario que se quiere actualizar
        ObjectId objectId = new ObjectId(account.idUser());
        Optional<User> userOptional = userRepository.findById(String.valueOf(objectId));


        //Si no se encontró el usuario, lanzamos una excepción
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("No se encontró el usuario con el id "+account.idUser());
        }


        // Mapear los datos actualizados al usuario existente
        User user = userOptional.get();
        userMapper.toDocument(account, user);


        //Como el objeto usuario ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        userRepository.save(user);

    }

    @Override
    public void deleteUser(String id) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new UserNotFoundException("No se encontró el usuario con el id "+id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<User> userOptional = userRepository.findById(String.valueOf(objectId));

        //Si no se encontró el usuario, lanzamos una excepción
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("No se encontró el usuario con el id "+id);
        }

        //Obtenemos el usuario que se quiere eliminar y le asignamos el estado eliminado
        User user = userOptional.get();
        user.setStatus(UserStatus.DELETED);

        //Como el objeto usuario ya tiene un id, el save() no crea un nuevo registro sino que actualiza el que ya existe
        userRepository.save(user);

    }

    @Override
    public UserDTO getUser(String id) throws Exception {
        //Validamos el id
        if (!ObjectId.isValid(id)) {
            throw new UserNotFoundException("No se encontró el usuario con el id "+id);
        }

        //Buscamos el usuario que se quiere obtener
        ObjectId objectId = new ObjectId(id);
        Optional<User> userOptional = userRepository.findById(String.valueOf(objectId));

        //Si no se encontró el usuario, lanzamos una excepción
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("No se encontró el usuario con el id "+id);
        }

        //Retornamos el usuario encontrado convertido a DTO
        return userMapper.toDTO(userOptional.get());
    }

    @Override
    public List<UserDTO> listAllUsers(UserFilterDTO filter) {
        // Validar que la página no sea menor a 0
        if(filter.page() < 0) throw new RuntimeException("La página no puede ser menor a 0");


        // Crear criterios dinámicos
        Criteria criteria = new Criteria();


        if (filter.name() != null && !filter.name().isEmpty()) {
            criteria.and("name").regex(filter.name(), "i"); // Ignora a mayúsculas/minúsculas
        }


        if (filter.nameMunicipality() != null && !filter.nameMunicipality().isEmpty()) {
            criteria.and("municipality").regex(filter.nameMunicipality(), "i");
        }


        // Crear la consulta con los criterios y la paginación de 5 elementos por página
        Query query = new Query(criteria).with(PageRequest.of(filter.page(), 5));


        List<User> list = mongoTemplate.find(query, User.class);


        // Convertir la lista de usuarios a una lista de DTOs
        return list.stream()
                .map(userMapper::toDTO)
                .toList();

    }

    @Override
    public void changePassword(String email, String code, String newPass) throws Exception {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("El correo no existe");
        }
        User user = userOptional.get();

        if( user.getValidationCode() == null ){
            throw new Exception("No hay un codigo de validacion");
        }

        if(! user.getValidationCode().getCode().equals(code) ){
            throw new Exception("El codigo de validacion es incorrecto");
        }

        if(!LocalDateTime.now().isBefore( user.getValidationCode().getDate().plusMinutes(15) )){
            throw new Exception("El codigo de validacion ya expiro");
        }
        user.setPassword(newPass);
        userRepository.save(user);
    }

    @Override
    public void activateAccount(String email, String code) throws Exception {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("El correo no existe");
        }
        User user = userOptional.get();

        if( user.getValidationCode() == null ){
            throw new Exception("No hay un codigo de validacion");
        }

        if(! user.getValidationCode().getCode().equals(code) ){
            throw new Exception("El codigo de validacion es incorrecto");
        }

        if(!LocalDateTime.now().isBefore( user.getValidationCode().getDate().plusMinutes(15) )){
            throw new Exception("El codigo de validacion ya expiro");
        }

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

    }

    @Override
    public void sendVerificationCode(String email) throws Exception {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("El correo no existe");
        }

        String code = generateAlphaNumericCode(9);
        ValidationCode validationCode = new ValidationCode(code, LocalDateTime.now());
        User user = userOptional.get();

        user.setValidationCode(validationCode);
        userRepository.save(user);

        String subject = "Código de verificación de tu cuenta";
        String body = "Hola " + user.getName() + ",\n\nTu código de verificación es: " + code +
                "\nEste código expirará en 15 minutos.\n\nGracias.";

        // Crear DTO y enviar correo
        EmailDTO emailDTO = new EmailDTO(subject, body, email);

        try {
            emailService.sendEmail(emailDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de verificación", e);
        }

    }
    public String generateAlphaNumericCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
