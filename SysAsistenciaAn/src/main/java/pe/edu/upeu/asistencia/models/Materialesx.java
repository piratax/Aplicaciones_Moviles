/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "asis_materialesx")
public class Materialesx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    @Column(name = "cui",length = 20,nullable = false)
    private String cui;  
    @Size(max = 20)
    @Column(name = "tipo_cui",length = 20,nullable = false)
    private String tipoCui; 
    @Column(name = "mater_entre", nullable = true, length = 200)
    private String materEntre;  
    @JsonFormat(pattern = "yyyy-MM-dd")    
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;  
    @JsonFormat(pattern = "HH:mm:ss") 
    @Basic(optional = false)    
    @Column(name = "hora_reg", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horaReg;    
    @Size(max = 60)
    private String latituda;
    @Size(max = 60)
    private String longituda; 
    @JsonFormat(pattern = "yyyy-MM-dd") 
    @Basic(optional = false)
    @Column(name = "mod_fh", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate modFh;     
    @Size(max = 2)
    @Column(name="offlinex",length = 2,nullable = false)
    private String offlinex;  
    
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    private Actividad actividadId;     
}
