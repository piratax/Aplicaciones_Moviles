/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Facultad;

/**
 *
 * @author DELL
 */
public interface FacultadService {
    Facultad save(Facultad entidad);

    List<Facultad> findAll();

    Map<String, Boolean> delete(Long id);

    Facultad geEntidadById(Long id);

    Facultad update(Facultad entidad, Long id); 
}
