/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;
import pe.edu.upeu.asistencia.models.Periodo;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
public interface PeriodoService {

    Periodo save(Periodo periodo);

    List<Periodo> findAll();

    Map<String, Boolean> delete(Long id);

    Periodo getPeriodoById(Long id);

    Periodo update(Periodo periodo, Long id);

}
