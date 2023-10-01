package pe.edu.upeu.asistencia.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "gobal_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    @Size(max = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    @Size(max = 100)
    private String apellidos;

    @Column(nullable = false)
    @Size(max = 100)
    private String correo;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;
    
    @Column(nullable = false)
    @Size(max = 12)
    private String dni;
    
    @Column(name = "perfil_prin", nullable = false)
    @Size(max = 20)
    private String perfilPrin;
    
    @Column(nullable = false)
    @Size(max = 8)
    private String estado;
    
    @Column(nullable = false)
    @Size(max = 2)
    private String offlinex;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "global_usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();  
    
    @Transient
    private String rolx;
}
