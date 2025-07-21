package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOTransporte {
    private Long idTransporte;

    // Campos para Transportista
    @NotNull(message = "El id del transportista es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    private Long idTransportista;

    private String nombreTransportista;
    private String apellidoTransportista;
    private String telefonoTransportista;
    private String correoTransportista;
    private String nitTransportista;

    private Long idUsuarioTransportista;
    private String nombreUsuario;
    private String contrasenia;



    // Campos para ServicioTransporte
    @NotNull(message = "El id del servicio de transporte es obligatorio", groups = {OnCreate.class, OnUpdate.class})
    private Long idServicioTransporte;

    private String placaServicio;
    private String tarjetaCirculacionServicio;
    private String capacidadServicio;

    public interface OnCreate{}
    public interface OnUpdate{}
}