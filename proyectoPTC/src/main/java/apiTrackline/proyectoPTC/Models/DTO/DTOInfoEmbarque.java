package apiTrackline.proyectoPTC.Models.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DTOInfoEmbarque {

    private Long idInfoEmbarque;

    @Size(max = 40, message = "El campo 'facturas' debe tener como máximo 40 caracteres")
    private String facturas;

    // Solo letras, espacios y caracteres acentuados
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El proveedor solo debe contener letras y espacios")
    @Size(max = 50, message = "El campo 'proveedorRef' debe tener como máximo 50 caracteres")
    private String proveedorRef;

    @Min(value = 0, message = "El número de bultos no puede ser negativo")
    private Long bultos;

    // Solo letras y espacios
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]*$", message = "El tipo solo debe contener letras y espacios")
    @Size(max = 50, message = "El campo 'tipo' debe tener como máximo 50 caracteres")
    private String tipo;

    @Min(value = 0, message = "Los kilos no pueden ser negativos")
    private Double kilos;

    @Min(value = 0, message = "El volumen no puede ser negativo")
    private Double volumen;
}
