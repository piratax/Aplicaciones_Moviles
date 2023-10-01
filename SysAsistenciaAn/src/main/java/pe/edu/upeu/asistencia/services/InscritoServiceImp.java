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
import pe.edu.upeu.asistencia.models.Inscrito;
import pe.edu.upeu.asistencia.repositories.InscritoRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class InscritoServiceImp implements InscritoService {

    @Autowired
    private InscritoRepository inscritoRepo;

    @Override
    public Inscrito save(Inscrito inscrito) {
        return inscritoRepo.save(inscrito);
    }

    @Override
    public List<Inscrito> findAll() {
        return inscritoRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad not exist with id :" + id));
        inscritoRepo.delete(inscritox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Inscrito getEntidadById(Long id) {
        Inscrito findInscrito = inscritoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activiad not exist with id :" + id));
        return findInscrito;
    }

    @Override
    public Inscrito update(Inscrito activiad, Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        inscritox.setCui(activiad.getCui());
        inscritox.setTipoCui(activiad.getTipoCui());        
        inscritox.setOfflinex(activiad.getOfflinex());
        return inscritoRepo.save(inscritox);         
    }

}
