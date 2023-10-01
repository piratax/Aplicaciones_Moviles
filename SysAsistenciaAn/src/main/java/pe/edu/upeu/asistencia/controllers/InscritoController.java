/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upeu.asistencia.dtos.InscritoxDto;
import pe.edu.upeu.asistencia.models.Inscritox;
import pe.edu.upeu.asistencia.services.InscritoService;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/inscrito")
public class InscritoController {

    @Autowired
    private InscritoService inscritoService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Inscritox>> listInscritox() {
        List<Inscritox> actDto = inscritoService.findAll();
        return ResponseEntity.ok(actDto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Inscritox> createInscritox(@RequestBody InscritoxDto.InscritoxCrearDto inscritox) {
        Inscritox data = inscritoService.save(inscritox);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Inscritox> getInscritoxById(@PathVariable Long id) {
        Inscritox inscritox = inscritoService.getInscritoxById(id);
        return ResponseEntity.ok(inscritox);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInscritox(@PathVariable Long id) {
        Inscritox inscritox = inscritoService.getInscritoxById(id);
        return ResponseEntity.ok(inscritoService.delete(inscritox.getId()));
    }

        @PutMapping("/editar/{id}")
    public ResponseEntity<Inscritox> updateInscritox(@PathVariable Long id,
            @RequestBody InscritoxDto.InscritoxCrearDto inscritoxDetails) {
        Inscritox updatedInscritox= inscritoService.update(inscritoxDetails, id);
        return ResponseEntity.ok(updatedInscritox);
    }


    
}
