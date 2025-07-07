package apiTrackline.proyectoPTC.Entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TIPOSERVICIO")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TipoServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_TS")
    @SequenceGenerator(name = "seq_id_TS", sequenceName = "seq_id_TS", allocationSize = 0)
    @Column(name = "IDTIPOSERVICIO")
    private Long idTipoServicio;

    @Column(name = "TIPOSERVICIO")
    private String tipoServicio;
}
