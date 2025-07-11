package apiTrackline.proyectoPTC.Models.DTO;

import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El nombre no puede estar en blanco.", groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres.", groups = {OnCreate.class, OnUpdate.class})
    private String nombre;

    @Size(max = 100, message = "El apellido no debe exceder 100 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String apellido;

    @Size(max = 15, message = "El teléfono no debe exceder 15 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String telefono;

    @Email(message = "El correo debe ser válido", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    @Size(max = 100, message = "El correo no debe exceder 100 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    @Pattern(
            regexp = "^[\\w.%+-]+@(gmail\\.com|yahoo\\.com)$",
            message = "El correo solo puede ser de dominio @gmail.com o @yahoo.com",
            groups = {OnCreate.class, OnUpdate.class, OnPatch.class}
    )
    private String correo;

    @Size(max = 20, message = "El NIT no debe exceder 20 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String nit;

    @NotNull(message = "El ID del usuario es obligatorio.", groups = {OnCreate.class, OnUpdate.class})
    private Long idUsuario;

    private String nombreUsuario;

    // Grupos de validación
    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}
