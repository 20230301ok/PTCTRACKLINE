package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTORecoleccion {

    private Long idRecoleccion;

    private Boolean transporte;
    private Boolean recoleccionEntrega;

    @Size(max = 10, message = "El máximo de caracteres para el numero de documento es 10")
    private String numeroDoc;

    @Size(max = 50, message = "El máximo de caracteres para el lugar de origen es 50")
    private String lugarOrigen;

    @Size(max = 50, message = "El máximo de caracteres para país de origen es 50")
    private String paisOrigen;

    @Size(max = 50, message = "El máximo de caracteres para el lugar de destino es 50")
    private String lugarDestino;

    @Size(max = 50, message = "El máximo de caracteres para el país de destino es de 50")
    private String paisDestino;


}
