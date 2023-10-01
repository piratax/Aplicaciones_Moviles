/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.models.Materialesx;
/**
 *
 * @author DELL
 */
@Mapper(componentModel = "spring")
public interface MaterialesxMapper {
    MaterialesxDto toMaterialesxDto(Materialesx entidad);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "actividadId", ignore = true)
    Materialesx materialesxCrearDtoToMaterialesx(MaterialesxDto.MaterialesxCrearDto entidadCrearDto);    
}
