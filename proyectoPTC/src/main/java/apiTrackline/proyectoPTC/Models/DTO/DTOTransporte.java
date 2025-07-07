package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode
@Getter
@Setter
public class DTOTransporte {

    private Long idTransporte;
    private Long idTransportista;
    private Long idServicioTransporte;


}
