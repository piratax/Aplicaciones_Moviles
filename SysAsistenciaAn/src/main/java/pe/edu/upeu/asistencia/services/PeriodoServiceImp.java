/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;

import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.repositories.PeriodoRepository;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@RequiredArgsConstructor
@Service
@Transactional
public class PeriodoServiceImp implements PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepo;


    @Override
    public Periodo save(Periodo periodo) {
     
        Periodo savedUser = periodoRepo.save(periodo);
        return savedUser;
    }

    @Override
    public List<Periodo> findAll() {
        return periodoRepo.findAll();
    }

    @Override
    public Periodo getPeriodoById(Long id) {
        Periodo findPeriodo = periodoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        return findPeriodo;
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));

        periodoRepo.delete(periodox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Periodo update(Periodo periodo, Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        periodox.setNombre(periodo.getNombre());
        periodox.setEstado(periodo.getEstado());        
        
        return periodoRepo.save(periodox);
    }

}
