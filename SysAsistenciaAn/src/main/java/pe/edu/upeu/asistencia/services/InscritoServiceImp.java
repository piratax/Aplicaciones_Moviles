/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.asistencia.dtos.InscritoxDto;
import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.mappers.InscritoxMapper;
import pe.edu.upeu.asistencia.models.Inscritox;
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
    private ActividadService actividadService;

    private final InscritoxMapper inscritoxMapper;

    @Autowired
    private InscritoRepository inscritoRepo;



    @Override
    public Inscritox save(InscritoxDto.InscritoxCrearDto inscritox) {

        Inscritox matEnt=inscritoxMapper.inscritoxCrearDtoToInscritox(inscritox);
        matEnt.setActividadId(actividadService.getActividadById(inscritox.actividadId()));
        //matEnt.setModFh(null);
        try {
            return inscritoRepo.save(matEnt);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Inscritox> findAll() {
        try {
            return inscritoRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public Map<String, Boolean> delete(Long id) {
        Inscritox inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripcion not exist with id :" + id));
        inscritoRepo.delete(inscritox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Inscritox getInscritoxById(Long id) {
        Inscritox findInscrito = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia not exist with id :" + id));
        return findInscrito;
    }

    @Override
    public Inscritox update(InscritoxDto.InscritoxCrearDto inscritox, Long id) {
        Inscritox inscritoxx = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        inscritoxx.setCui(inscritox.cui());
        inscritoxx.setTipoCui(inscritox.tipoCui());
        inscritoxx.setEvidensPay(inscritox.evidensPay());
        inscritoxx.setOfflinex(inscritox.offlinex());
        return inscritoRepo.save(inscritoxx);
    }
}
