package apiTrackline.proyectoPTC.Models.DTO;

import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DTOTransportista {

    private Long idTransportista;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres.")
    private String nombre;

    @Size(max = 100, message = "El apellido no debe exceder 100 caracteres.")
    private String apellido;

    @Size(max = 15, message = "El teléfono no debe exceder 15 caracteres.")
    private String telefono;

    @Email(message = "El correo debe ser valido ")
    @Size(max = 100, message = "El correo no debe exceder 100 caracteres.")
    private String correo;

    @Size(max = 20, message = "El NIT no debe exceder 20 caracteres.")
    private String nit;


    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    private String nombreUsuario;
}
