/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.services.ActividadService;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/actividad")
public class ActividadController {
    
    @Autowired
    private ActividadService actividadService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Actividad>> listActividad() {
        List<Actividad> actDto = actividadService.findAll();
        
        //Gson gson = new Gson();
        //String jsonCartList = gson.toJson(actDto);
        //System.out.println("Ver Aqui: "+jsonCartList);
        return ResponseEntity.ok(actDto);
        //return new ResponseEntity<>(actDto, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<Actividad> createActividad(@RequestBody Actividad actividad) {
        Actividad data = actividadService.save(actividad);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Actividad> getActividadById(@PathVariable Long id) {
        Actividad actividad = actividadService.getActividadById(id);
        return ResponseEntity.ok(actividad);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteActividad(@PathVariable Long id) {
        Actividad actividad = actividadService.getActividadById(id);
        return ResponseEntity.ok(actividadService.delete(actividad.getId()));
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable Long id, @RequestBody Actividad actividadDetails) {        
        Actividad updatedActividad = actividadService.update(actividadDetails, id);
        return ResponseEntity.ok(updatedActividad);
    }      
}
