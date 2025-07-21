package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOViaje {

    private Long idViaje;

    @NotNull(message = "El ID de orden servicio es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    private Long idOrdenServicio;

    @NotNull(message = "El ID de transporte es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    private Long idTransporte;

    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}
