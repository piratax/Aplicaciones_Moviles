/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "upeu_matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    @Size(max = 8)
    @Column(name = "estado", nullable = false, length = 8)
    private String estado;  
    @Column(name = "evento_id", nullable = true)
    private Long eventoId; 
    
    @JoinColumn(name = "periodo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"matriculas", "eventos"})
    private Periodo periodoId;   
    
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"matriculas", "eventos"})
    private Persona personaId;    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "matricula_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"matriculaId"})
    public List<Materiales> materialeses; 

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "matricula_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"matriculaId"})
    public List<Asistencia> asistencias;     
}
