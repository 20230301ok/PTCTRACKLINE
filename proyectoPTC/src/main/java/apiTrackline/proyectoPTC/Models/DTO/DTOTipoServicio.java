package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private String tipoServicio;
}
