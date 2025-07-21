package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CARGOS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CargosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCARGOS")
    private Long idCargos;

    @ManyToOne
    @JoinColumn(name = "IDTIPODATOCONTABLE", referencedColumnName = "IDTIPODATOCONTABLE")
    private TipoDatoContableEntity tipoDatoContable;

    @Column(name = "MONTO")
    private Long monto;
}
