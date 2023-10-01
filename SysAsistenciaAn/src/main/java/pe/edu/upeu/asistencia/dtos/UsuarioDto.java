package pe.edu.upeu.asistencia.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String token;
    private String dni;
    private String perfilPrin;
    private String estado;
    private String offlinex;

}
