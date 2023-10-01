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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.AppException;

import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;

import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.repositories.ActividadRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class ActividadServiceImp implements ActividadService{

    @Autowired
    private ActividadRepository actividadRepo;


    
    @Override
    public Actividad save(Actividad activiad) {
        
        try {
            return actividadRepo.save(activiad);
        } catch (Exception e) {
            throw new AppException("Error-"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Actividad> findAll() {
        try {
            return actividadRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Actividad actividadx = actividadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad not exist with id :" + id));

        actividadRepo.delete(actividadx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;        
    }

    @Override
    public Actividad getActividadById(Long id) {
        Actividad findActividad = actividadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activiad not exist with id :" + id));
        return findActividad;        
    }

    @Override
    public Actividad update(Actividad activiad, Long id) {
        Actividad actividadx = actividadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        actividadx.setFecha(activiad.getFecha());
        actividadx.setHorai(activiad.getHorai());        
        actividadx.setEstado(activiad.getEstado());
        return actividadRepo.save(actividadx);        
    }
    
}
