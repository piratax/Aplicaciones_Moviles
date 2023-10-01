/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Persona;

/**
 *
 * @author DELL
 */
public interface PersonaService {
    Persona save(Persona entidad);

    List<Persona> findAll();

    Map<String, Boolean> delete(Long id);

    Persona geEntidadById(Long id);

    Persona update(Persona entidad, Long id);     
}
