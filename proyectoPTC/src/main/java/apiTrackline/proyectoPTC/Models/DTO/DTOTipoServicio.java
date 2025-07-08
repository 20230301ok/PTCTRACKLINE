package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

@Getter @Setter @ToString
@EqualsAndHashCode
public class DTOTipoServicio {
    private Long idTipoServicio;

    @NotBlank(message = "El tipo de servicio no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El tipo de servicio solo debe contener letras y espacios")
    @Size(max = 20, message = "El máximo de caracteres para el tipo de servicio es 20")
    private String tipoServicio;
}
