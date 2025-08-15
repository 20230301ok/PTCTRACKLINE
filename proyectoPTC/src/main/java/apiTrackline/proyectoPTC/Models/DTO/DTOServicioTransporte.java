package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Size(max = 70, message = "La placa no debe exceder 70 caracteres.", groups = {OnCreate.class, OnPatch.class, OnUpdate.class})
    @NotBlank(message = "El número de placa no puede estar en blanco", groups = {OnCreate.class, OnUpdate.class})
    private String placa;

    @Size(max = 50, message = "La tarjeta de circulacion no debe exceder 50 caracteres.", groups = {OnCreate.class, OnPatch.class, OnUpdate.class})
    @NotBlank(message = "La tarjeta de circulación no puede estar en blanco", groups = {OnCreate.class, OnUpdate.class})
    private String tarjetaCirculacion;

    @Size(max = 50, message = "La capacidad no debe exceder 50 caracteres.", groups = {OnCreate.class, OnPatch.class, OnUpdate.class})
    private String capacidad;

    public interface OnCreate {}
    public interface OnUpdate {}
    public interface OnPatch {}
}

