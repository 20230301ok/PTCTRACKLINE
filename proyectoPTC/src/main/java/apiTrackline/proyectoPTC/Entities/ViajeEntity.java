package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @EqualsAndHashCode @ToString
@Entity
@Table(name = "TB_VIAJES")
public class ViajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVIAJE")
    private Long idViaje;

    //Relacion con OrdenServicio
    @ManyToOne
    @JoinColumn(name = "IDORDENSERVICIO", referencedColumnName = "IDORDENSERVICIO")
    private OrdenServicioEntity OrdenServicio;

    //Relacion con Transporte
    @ManyToOne
    @JoinColumn(name = "IDTRANSPORTE", referencedColumnName = "IDTRANSPORTE")
    private TransporteEntity transporte;
}
