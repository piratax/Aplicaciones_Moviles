/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;

import pe.edu.upeu.asistencia.dtos.MaterialesxDto;
import pe.edu.upeu.asistencia.models.Materialesx;

/**
 *
 * @author DELL
 */
public interface MaterialesxService {
    Materialesx save(MaterialesxDto.MaterialesxCrearDto materialesx);

    List<Materialesx> findAll();

    Map<String, Boolean> delete(Long id);

    Materialesx getMaterialesxById(Long id);

    Materialesx update(MaterialesxDto.MaterialesxCrearDto materialesx, Long id);
}
