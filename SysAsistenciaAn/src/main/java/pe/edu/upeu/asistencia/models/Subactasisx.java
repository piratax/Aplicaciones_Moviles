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
 * @author DELL
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asis_subactasisx")
public class Subactasisx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")    
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "horasi", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horasi;
    @Basic(optional = false)
    @Column(name = "min_toler", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date minToler;
    @Size(max = 8)
    private String estado;    
    @Size(max = 2)
    @Column(name="offlinex",length = 2,nullable = false)
    private String offlinex;   
    
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"asistenciaxs", "inscritos", "subactasisxs", "materialesxs"})
    private Actividad actividadId;      
}
