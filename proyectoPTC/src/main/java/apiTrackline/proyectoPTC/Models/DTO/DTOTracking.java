package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DTOTracking {

    private Long idTracking;

    @Positive(message = "El id de viaje no puede ser negativo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idViaje;
    @Positive(message = "El id de estado no puede ser negativo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idEstado;

    private LocalDateTime horaEstimadaPartida;
    private LocalDateTime horaEstimadaLlegada;
    private LocalDateTime horaLlegada;
    private LocalDateTime horaSalida;

    @NotBlank(message = "El lugar de partida no puede estar vacío", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 70, message = "El lugar de partida debe tener entre 3 y 70 caracteres", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String lugarPartida;

    @NotBlank(message = "El lugar de llegada no puede estar vacío", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 70, message = "El lugar de llegada debe tener entre 3 y 70 caracteres", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String lugarLlegada;

    public interface OnCreate{}
    public interface OnUpdate{}
    public interface OnPatch{}
}
