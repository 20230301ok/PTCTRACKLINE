package apiTrackline.proyectoPTC.Models.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOOrdenServicio {
    private Long idOrdenServicio;
    private String clienteNIT;
    private Long idOrdenEncabezado;
    private Long idInfoEmbarque;
    private Long idAduana;
    private Long idTransporte;
    private Long idRecoleccion;
    private Long idCargos;
    private Long idFinanciamiento;
    private Long idObservaciones;
}