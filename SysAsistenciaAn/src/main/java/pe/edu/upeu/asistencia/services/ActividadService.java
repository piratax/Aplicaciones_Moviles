/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;

import pe.edu.upeu.asistencia.models.Actividad;

/**
 *
 * @author DELL
 */
public interface ActividadService {
     Actividad save(Actividad activiad);

    List<Actividad> findAll();

    Map<String, Boolean> delete(Long id);

    Actividad getActividadById(Long id);

    Actividad update(Actividad activiad, Long id);   
}
