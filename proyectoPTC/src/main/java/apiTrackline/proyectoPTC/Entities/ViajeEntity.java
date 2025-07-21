package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_VIAJES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ViajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVIAJE")
    private Long idViaje;

    @ManyToOne
    @JoinColumn(name = "IDORDENSERVICIO", referencedColumnName = "IDORDENSERVICIO")
    private OrdenServicioEntity OrdenServicio;

    @ManyToOne
    @JoinColumn(name = "IDTRANSPORTE", referencedColumnName = "IDTRANSPORTE")
    private TransporteEntity idTransporte;
}