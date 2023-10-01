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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "upeu_evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(name = "nombre_evento", nullable = false)
    private String nombreEvento;  
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;  
    @Basic(optional = false)
    @Column(name = "horai", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horai; 
    @Basic(optional = false)
    @Column(name = "min_toler", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date minToler; 
    @Size(max = 60)
    private String latitud;
    @Size(max = 60)
    private String longitud;   
    @Size(max = 8)
    private String estado;
    @Size(max = 2)
    @Column(name = "evaluar", nullable = false, length = 2)
    private String evaluar; 
    @Column(name = "perfil_evento", nullable = false, length = 20)
    private String perfilEvento;
    @Column(name = "user_create", nullable = false, length = 100)
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
    
    @JoinColumn(name = "periodo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"matriculas", "eventos"})
    private Periodo periodoId;    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"eventoId"})
    public List<Subactasis> subactasises; 
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"eventoId"})
    public List<Materiales> materialeses;  
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"eventoId"})
    public List<Asistencia> asistencias;    
}
