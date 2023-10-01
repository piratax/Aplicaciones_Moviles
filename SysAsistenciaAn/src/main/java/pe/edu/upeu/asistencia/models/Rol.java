package pe.edu.upeu.asistencia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "gobal_rol")
public class Rol {

    public enum RolNombre {//Insertar Manualmente en la tabla global_rol ambas opciones
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol_nombre", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

}
