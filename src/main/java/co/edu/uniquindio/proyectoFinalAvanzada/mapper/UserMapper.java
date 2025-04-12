package co.edu.uniquindio.proyectoFinalAvanzada.mapper;

import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.CreateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UpdateUserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.dto.users.UserDTO;
import co.edu.uniquindio.proyectoFinalAvanzada.model.documents.User;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "rol", constant = "CLIENT")
    @Mapping(target = "status", constant = "INACTIVE")
    @Mapping(target = "registerDate", expression = "java(java.time.LocalDateTime.now())")
    User toDocument(CreateUserDTO createUserDTODTO);


    UserDTO toDTO(User user);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }

    void toDocument(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}
