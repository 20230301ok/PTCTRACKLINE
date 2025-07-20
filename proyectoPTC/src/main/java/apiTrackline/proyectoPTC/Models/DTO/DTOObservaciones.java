package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOObservaciones {

    private Long idObservaciones;

    @NotNull(message = "El ID del selectivo es obligatorio.", groups = {OnCreate.class, OnUpdate.class})
    private Long idSelectivo;

    @Size(max = 50, message = "La observación no debe exceder los 50 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String observaciones;

    private String colorSelectivo;

    // Grupos de validación
    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}
