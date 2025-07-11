package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_SERVICIOTRANSPORTE")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServicioTransporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDSERVICIOTRANSPORTE")
    private Long idServicioTransporte;

    @Column(name = "PLACA")
    private String placa;

    @Column(name = "TARJETACIRCULACION")
    private String tarjetaCirculacion;

    @Column(name = "CAPACIDAD")
    private String capacidad;
}
