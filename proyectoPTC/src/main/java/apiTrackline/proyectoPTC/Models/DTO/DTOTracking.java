package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class DTOTracking {

    private Long idTracking;

    // Campos relacionados con Viaje
    @Positive(message = "El id de viaje no puede ser negativo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idViaje;


    // Desde Viaje -> OrdenServicio (solo campos necesarios)
    private Long idOrdenServicio;
    private String clienteNIT;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefonoCliente;
    private String correoCliente;
    private String codEmpresaCliente;
    private Long idUsuarioCliente;
    private String nombreUsuarioCliente;

    // Desde Viaje -> Transporte (solo campos necesarios)
    private Long idTransporte;
    private String placaServicio;
    private String tarjetaCirculacionServicio;
    private String capacidadServicio;

    // Estado
    @Positive(message = "El id de estado no puede ser negativo", groups = {OnCreate.class, OnUpdate.class, OnPatch.class})
    private Long idEstado;

    // Campos propios Tracking
    private Timestamp horaEstimadaPartida;
    private Timestamp horaEstimadaLlegada;
    private Timestamp horaSalida;
    private Timestamp horaLlegada;

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
