package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EstadosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDESTADO")
    private Long idEstado;

    //Estados -------- Selectivo
    @ManyToOne
    @JoinColumn(name = "IDSELECTIVO", referencedColumnName = "IDSELECTIVO")
    private SelectivoEntity Selectivo;

    private Boolean documentos;
    private Boolean clasificacion;
    private Boolean digitacion;
    private Boolean registro;
    private Boolean pago;
    private Boolean levantePago;
    private Boolean equipoTransporte;
    private Boolean carga;
    private Boolean enCamino;
    private Boolean entrega;
    private Boolean facturacion;

    //Estados -------- Ordenes de servicio
    @ManyToOne
    @JoinColumn(name = "IDORDENSERVICIO", referencedColumnName = "IDORDENSERVICIO")
    private OrdenServicioEntity OrdenServicioEstados;
}
