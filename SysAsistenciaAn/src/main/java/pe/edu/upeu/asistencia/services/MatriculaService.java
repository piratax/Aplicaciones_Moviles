/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.;

/**
 *
 * @author MeMatricula
 */
public interface MatriculaService {
    Matricula save(Matricula entidad);

    List<Matricula> findAll();

    Map<String, Boolean> delete(Long id);

    Matricula geEntidadById(Long id);

    Matricula update(Matricula entidad, Long id); 
}
