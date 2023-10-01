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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asis_asistenciax")
public class Asistenciax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")    
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)    
    @Column(name = "hora_reg", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaReg;
    @Size(max = 60)
    private String latituda;
    @Size(max = 60)
    private String longituda;    
    @Size(max = 20)
    @Column(name = "tipo",length = 20,nullable = false)
    private String tipo;
    @Column(name = "calificacion", nullable = false)
    private int calificacion; 
    @Size(max = 20)
    @Column(name = "cui",length = 20,nullable = false)
    private String cui;    
    @Size(max = 20)
    @Column(name = "tipo_cui",length = 20,nullable = false)
    private String tipoCui;    
    @Column(name = "entsal", nullable = false, length = 2)
    private String entsal;
    
    @Column(name = "subactasis_id", nullable = true)
    private Long subactasisId;      
    
    @Size(max = 2)
    @Column(name="offlinex",length = 2,nullable = false)
    private String offlinex;  
    
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    private Actividad actividadId;  
}
