package apiTrackline.proyectoPTC.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class DTOOrdenEncabezado {
    private Long IdOrdenEncabezado;

    @NotBlank(message = "Fecha no puede estar en blanco.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotBlank(message = "Encargado no puede estar en blanco.")
    private String encargado;

    @NotBlank(message = "La referencia no puede estar en blanco.")
    private String referencia;

    @NotBlank(message = "El importador no puede estar en blanco.")
    private String importador;

    @NotBlank(message = "El NIT no puede estar en blanco.")
    private String NIT;
    private String registroIVA;
    private String facturaA;
    private String encargado2;
    private String NIT2;
    private String registroIVA2;
}
