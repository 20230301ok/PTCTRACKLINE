package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_ORDENSERVICIOS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrdenServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDORDENSERVICIO")
    private Long idOrdenServicio;

    @Column(name = "CLIENTENIT")
    private String clienteNIT;

    @ManyToOne
    @JoinColumn(name = "IDORDENENCABEZADO")
    private OrdenEncabezadoEntity idOrdenEncabezado;

    @ManyToOne
    @JoinColumn(name = "IDINFOEMBARQUE")
    private InfoEmbarqueEntity idInfoEmbarque;

    @ManyToOne
    @JoinColumn(name = "IDADUANA")
    private AduanaEntity idAduana;

    @ManyToOne
    @JoinColumn(name = "IDTRANSPORTE")
    private TransporteEntity idTransporte;

    @ManyToOne
    @JoinColumn(name = "IDRECOLECCION")
    private RecoleccionEntity idRecoleccion;

    @ManyToOne
    @JoinColumn(name = "IDCARGOS")
    private CargosEntity idCargos;

    @ManyToOne
    @JoinColumn(name = "IDFINANCIAMIENTO")
    private FinanciamientoEntity idFinanciamiento;

    @ManyToOne
    @JoinColumn(name = "IDOBSERVACIONES")
    private ObservacionesEntity idObservaciones;
}
