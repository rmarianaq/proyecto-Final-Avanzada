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
    @Mapping(target = "name", source = "name")
    User toDocument(CreateUserDTO createUserDTO);


    @Mapping(target = "id", ignore = true) // si no quieres sobreescribir el id
    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "municipality", source = "municipality")
    @Mapping(target = "address", source = "address")
    void updateUserFromDTO(UpdateUserDTO dto, @MappingTarget User user);

    UserDTO toDTO(User user);

    // Metodo para mapear de ObjectId a String
    default String map(ObjectId value) {
        return value != null ? value.toString() : null;
    }
}
