/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.asistencia.models.Actividad;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialesxDto {

    Long id;
    String cui;
    String tipoCui;
    String materEntre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha;
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime horaReg;
    String latituda;
    String longituda;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate modFh;
    String offlinex;
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    Actividad actividadId;

    public record MaterialesxCrearDto(Long id, String cui, String tipoCui, String materEntre, LocalDate fecha,
            LocalTime horaReg, String latituda, String longituda, LocalDate modFh, String offlinex, Long actividadId) {

    }
}
