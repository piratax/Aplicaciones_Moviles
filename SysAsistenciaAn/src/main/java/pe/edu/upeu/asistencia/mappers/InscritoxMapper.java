package pe.edu.upeu.asistencia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.edu.upeu.asistencia.dtos.InscritoxDto;
import pe.edu.upeu.asistencia.models.Inscritox;

/**
 *
 * @author DELL
 */

@Mapper(componentModel = "spring")

public interface InscritoxMapper {

    InscritoxDto toInscritoxDto(Inscritox entidad);

    @Mapping(target = "actividadId", ignore = true)
    Inscritox inscritoxCrearDtoToInscritox(InscritoxDto.InscritoxCrearDto entidaCrearDto);

    
    
}
