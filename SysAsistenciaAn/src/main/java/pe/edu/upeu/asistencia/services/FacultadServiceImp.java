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
import pe.edu.upeu.asistencia.models.Facultad;
import pe.edu.upeu.asistencia.repositories.FacultadRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class FacultadServiceImp implements FacultadService {

    @Autowired
    private FacultadRepository entidadRepo;

    @Override
    public Facultad save(Facultad entidad) {
        return entidadRepo.save(entidad);
    }

    @Override
    public List<Facultad> findAll() {
        return entidadRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Facultad entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facultad not exist with id :" + id));
        entidadRepo.delete(entidadx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Facultad geEntidadById(Long id) {
        Facultad findEntidad = entidadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Facultad not exist with id :" + id));
        return findEntidad;
    }

    @Override
    public Facultad update(Facultad entidad, Long id) {
        Facultad entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        entidadx.setNombrefac(entidad.getNombrefac());
        entidadx.setEstado(entidad.getEstado());
        entidadx.setIniciales(entidad.getIniciales());
        return entidadRepo.save(entidadx);
    }

}
