package apiTrackline.proyectoPTC.Models.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DTOTracking {

    private Long idTracking;

    private Long idViaje;
    private Long idEstado;

    private LocalDateTime horaEstimadaPartida;
    private LocalDateTime horaEstimadaLlegada;
    private LocalDateTime horaLlegada;
    private LocalDateTime horaSalida;

    private String lugarPartida;
    private String lugarLlegada;
}
