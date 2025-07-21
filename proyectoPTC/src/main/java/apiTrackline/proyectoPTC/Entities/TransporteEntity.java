package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
@Entity
@Table(name = "TB_TRANSPORTE")
public class TransporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTRANSPORTE")
    private Long idTransporte;

    @ManyToOne
    @JoinColumn(name = "IDTRANSPORTISTA", referencedColumnName = "IDTRANSPORTISTA")
    private TransportistaEntity transportista;

    @ManyToOne
    @JoinColumn(name = "IDSERVICIOTRANSPORTE", referencedColumnName = "IDSERVICIOTRANSPORTE")
    private ServicioTransporteEntity servicioTransporte;
}
