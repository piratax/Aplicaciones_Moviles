/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD IMPORTANTE
 * https://github.com/FasterXML/jackson-databind/issues/2011
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asis_actividad")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_actividad", nullable = false)
    private String nombreActividad;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    @Basic(optional = false)
    @Column(name = "horai", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horai;

    @JsonFormat(pattern = "HH:mm:ss")
    @Basic(optional = false)
    @Column(name = "min_toler", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime minToler;
    @Size(max = 60)
    private String latitud;
    @Size(max = 60)
    private String longitud;
    @Size(max = 8)
    private String estado;
    @Size(max = 2)
    @Column(name = "evaluar", nullable = false, length = 2)
    private String evaluar;
    @Column(name = "user_create", nullable = false)
    private String userCreate;
    @Column(name = "mater", nullable = true, length = 200)
    private String mater;
    @Column(name = "valid_insc", nullable = false, length = 2)
    private String validInsc;
    @Column(name = "asis_subact", nullable = false, length = 2)
    private String asisSubact;
    @Column(name = "entsal", nullable = false, length = 2)
    private String entsal;
    @Size(max = 2)
    @Column(name = "offlinex", length = 2, nullable = false)
    private String offlinex;

    //@JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"actividadId"})
    public List<Asistenciax> asistenciaxs;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    //@JsonBackReference
    @JsonIgnoreProperties({"actividadId"})
    public List<Inscrito> inscritos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"actividadId"})
    public List<Subactasisx> subactasisxs;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"actividadId"})
    public List<Materialesx> materialesxs;

}
