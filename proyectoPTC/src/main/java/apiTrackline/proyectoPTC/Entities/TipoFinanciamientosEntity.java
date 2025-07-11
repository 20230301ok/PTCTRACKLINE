package apiTrackline.proyectoPTC.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TIPOFINANCIAMIENTOS")
@Getter
@Setter
@ToString
public class TipoFinanciamientosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTIPOFINANCIAMIENTO")
    private Long idTipoFinanciamiento;

    @Column(name = "NOMBRE")
    private String nombre;
}

