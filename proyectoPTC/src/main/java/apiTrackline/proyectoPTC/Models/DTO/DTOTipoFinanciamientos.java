package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DTOTipoFinanciamientos {

    private Long idTipoFinanciamiento;

    private String nombre;
}