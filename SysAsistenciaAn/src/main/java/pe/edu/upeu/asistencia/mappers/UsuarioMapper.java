package pe.edu.upeu.asistencia.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.asistencia.dtos.UsuarioCrearDto;
import pe.edu.upeu.asistencia.dtos.UsuarioDto;
import pe.edu.upeu.asistencia.models.Usuario;



@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toUserDto(Usuario user);

    @Mapping(target = "password", ignore = true)
    Usuario usuarioCrearDtoToUser(UsuarioCrearDto usuarioCrearDto);

}
