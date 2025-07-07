package apiTrackline.proyectoPTC.Models.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DTOUsuario {
    private Long idUsuario;
    private String usuario;
    private String contrasenia;
    private Long idRol;
}


