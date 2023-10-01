package pe.edu.upeu.asistencia.dtos;

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
public class InscritoxDto {
    
    Long id;
    String cui;
    String tipoCui;
    String evidensPay;
    String offlinex; 

    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    Actividad actividadId;

     public record InscritoxCrearDto(Long id, String cui, String tipoCui,
                                     String evidensPay, String offlinex, Long actividadId) {

    }
}
