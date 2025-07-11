package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TRANSPORTE")
@Getter
@Setter
@EqualsAndHashCode
public class TransporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDTRANSPORTE")
    private Long idTransporte;

    @ManyToOne
    @JoinColumn(name = "IDTRANSPORTISTA") private TransportistaEntity idTransportista;

    @ManyToOne
    @JoinColumn(name = "IDSERVICIOTRANSPORTE") private TransportistaEntity idServicioTransporte;

}
