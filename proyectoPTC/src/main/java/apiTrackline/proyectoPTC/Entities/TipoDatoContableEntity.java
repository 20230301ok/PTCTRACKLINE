package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TIPODATOSCONTABLES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TipoDatoContableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_TDC")
    @SequenceGenerator(name = "seq_id_TDC", sequenceName = "SEQ_ID_TDC", allocationSize = 1)
    @Column(name = "IDTIPODATOCONTABLE")
    private Long idTipoDatoContable;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;
}
