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

import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.services.PeriodoService;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/asis/periodo")
public class PeriodoController {
    
    @Autowired
    private PeriodoService periodoService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Periodo>> listPeriodo() {
        List<Periodo> userDto = periodoService.findAll();
        return ResponseEntity.ok(userDto);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<Periodo> createPeriodo(@RequestBody Periodo periodo) {
        Periodo data = periodoService.save(periodo);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Periodo> getPeriodoById(@PathVariable Long id) {
        Periodo periodo = periodoService.getPeriodoById(id);
        return ResponseEntity.ok(periodo);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePeriodo(@PathVariable Long id) {
        Periodo periodo = periodoService.getPeriodoById(id);
        return ResponseEntity.ok(periodoService.delete(periodo.getId()));
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Periodo> updatePeriodo(@PathVariable Long id, @RequestBody Periodo periodoDetails) {        
        Periodo updatedPeriodo = periodoService.update(periodoDetails, id);
        return ResponseEntity.ok(updatedPeriodo);
    }    
}
