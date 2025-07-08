package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class DTOAduana {
    private Long idAduana;

    @NotBlank(message = "El id del tipo de servicio es obligatorio")
    private Long idTipoServicio;

    private String nombreTipoServicio;

    @Size(max = 50, message = "El máximo de caracteres para DM es 50")
    private String DM;

    @Size(max = 10, message = "El máximo de caracteres para la primera modalidad es 10")
    private String primeraModalidad;

    @Size(max = 10, message = "El máximo de caracteres para la segunda modalidad es 10")
    private String segundaModalidad;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El digitador solo debe contener letras y espacios")
    @Size(max = 30, message = "El máximo de caracteres para el digitador es 30")
    private String digitador;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El tramitador solo debe contener letras y espacios")
    @Size(max = 50, message = "El máximo de caracteres para el tramitador es 50")
    private String tramitador;
}
