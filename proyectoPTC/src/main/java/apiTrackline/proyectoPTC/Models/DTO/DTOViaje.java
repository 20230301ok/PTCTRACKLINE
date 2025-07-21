package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOViaje {

    private Long idViaje;

    @NotNull(message = "El ID de orden de servicio es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    @Positive(message = "El ID de orden de servicio debe ser positivo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idOrdenServicio;

    // Relaciones: Transporte
    @NotNull(message = "El ID de transporte es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    @Positive(message = "El ID de transporte debe ser positivo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idTransporte;

    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}
