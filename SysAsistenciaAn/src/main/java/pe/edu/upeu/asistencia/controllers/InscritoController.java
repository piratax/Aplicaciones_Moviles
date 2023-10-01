/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.asistencia.models.Inscrito;
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
    public ResponseEntity<List<Inscrito>> listActividad() {
        List<Inscrito> actDto = inscritoService.findAll();

        //Gson gson = new Gson();
        //String jsonCartList = gson.toJson(actDto);
        System.out.println("Ver Aqui: "+actDto.get(0).getTipoCui());
        System.out.println("Ver Aquix: "+actDto.get(0).getActividadId().getNombreActividad());
        return ResponseEntity.ok().body(actDto);
        //return new ResponseEntity<>(actDto, HttpStatus.OK);
    }
}
