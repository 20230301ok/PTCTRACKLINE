package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TRACKING")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTRACKING")
    private Long idTracking;

    @ManyToOne
    @JoinColumn(name = "IDVIAJE")
    private ViajeEntity idViaje;

    @ManyToOne
    @JoinColumn(name = "IDESTADO")
    private EstadosEntity idEstado;

    @Column(name = "HORAESTIMADAPARTIDA")
    private LocalDateTime horaEstimadaPartida;

    @Column(name = "HORAESTIMADALLEGADA")
    private LocalDateTime horaEstimadaLlegada;

    @Column(name = "HORALLEGADA")
    private LocalDateTime horaLlegada;

    @Column(name = "HORASALIDA")
    private LocalDateTime horaSalida;

    @Column(name = "LUGARPARTIDA")
    private String lugarPartida;

    @Column(name = "LUGARLLEGADA")
    private String lugarLlegada;
}
