package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_OBSERVACIONES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ObservacionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOBSERVACIONES")
    private Long idObservaciones;

    @ManyToOne
    @JoinColumn(name = "IDSELECTIVO", referencedColumnName = "IDSELECTIVO")
    private SelectivoEntity idSelectivo;

    @Column(name = "OBSERVACIONES")
    private String observaciones;
}
