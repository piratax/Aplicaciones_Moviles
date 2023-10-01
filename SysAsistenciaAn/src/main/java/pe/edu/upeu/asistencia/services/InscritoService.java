/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.List;
import java.util.Map;

import pe.edu.upeu.asistencia.dtos.InscritoxDto;
import pe.edu.upeu.asistencia.models.Inscritox;

/**
 *
 * @author DELL
 */
public interface InscritoService {

    Inscritox save(InscritoxDto.InscritoxCrearDto inscritox);

    List<Inscritox> findAll();

    Map<String, Boolean> delete(Long id);

    Inscritox getInscritoxById(Long id);

    Inscritox update(InscritoxDto.InscritoxCrearDto inscritox, Long id);
}
