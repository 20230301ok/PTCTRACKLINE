package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString @EqualsAndHashCode
public class DTOServicioTransporte {


    private Long idServicioTransporte;

    @Size(max = 70, message = "La placa no debe exceder 70 caracteres.")
    private String placa;

    @Size(max = 50, message = "La tarjeta de circulacion no debe exceder 50 caracteres.")
    private String tarjetaCirculacion;

    @Size(max = 50, message = "La capacidad no debe exceder 50 caracteres.")
    private String capacidad;

}

