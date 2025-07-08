package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 30, message = "El máximo de caracteres para el nombre de usuario es 30")
    private String usuario;

    @Size(max = 16, message = "El máximo de caracteres para la contraseña es 16")
    private String contrasenia;

    private Long idRol;

    private String rol;
}


