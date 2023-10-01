/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "upeu_persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;    
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;    
    @Column(name = "apellido_pat", nullable = false, length = 40)
    private String apellidoPat;    
    @Column(name = "apellido_mat", nullable = false, length = 40)
    private String apellidoMat;    
    @Column(name = "celular", nullable = false, length = 12)
    private String celular;    
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;    
    @Column(name = "clave", nullable = false, length = 255)
    private String clave;    
    @Column(name = "tipo", nullable = false, length = 12)
    private String tipo;    
    @Column(name = "estado", nullable = false, length = 8)
    private String estado;
    
    @JoinColumn(name = "escuela_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"personas"})
    private Escuela escuelaId;    
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"personaId"})
    public List<Matricula> matriculas;     
}
