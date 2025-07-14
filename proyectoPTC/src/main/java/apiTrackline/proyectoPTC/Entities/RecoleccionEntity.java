package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_RECOLECCION")
public class RecoleccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRECOLECCION")
    private Long idRecoleccion;

    @Column(name = "TRANSPORTE")
    private Boolean transporte;

    @Column(name = "RECOLECCIONENTREGA")
    private Boolean recoleccionEntrega;

    @Column(name = "NUMERODOC")
    private String numeroDoc;

    @Column(name = "LUGARORIGEN")
    private String lugarOrigen;

    @Column(name = "PAISORIGEN")
    private String paisOrigen;

    @Column(name = "LUGARDESTINO")
    private String lugarDestino;

    @Column(name = "PAISDESTINO")
    private String paisDestino;
}
