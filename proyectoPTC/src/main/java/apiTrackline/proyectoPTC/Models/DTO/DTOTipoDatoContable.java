package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOTipoDatoContable {

    private Long idTipoDatoContable;

    @NotBlank(message = "El nombre es obligatorio.", groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 30, message = "El nombre no debe exceder los 30 caracteres.", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private String nombre;

    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}
