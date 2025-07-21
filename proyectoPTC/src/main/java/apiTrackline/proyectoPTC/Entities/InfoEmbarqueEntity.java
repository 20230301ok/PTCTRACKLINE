package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_INFOEMBARQUE")
@Getter @Setter @ToString @EqualsAndHashCode
public class InfoEmbarqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDINFOEMBARQUE")
    private Long idInfoEmbarque;

    @Column(name = "FACTURAS")
    private String facturas;

    @Column(name = "PROVEEDORREF")
    private String proveedorRef;

    @Column(name = "BULTOS")
    private Long bultos;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "KILOS")
    private Long kilos;

    @Column(name = "VOLUMEN")
    private Long volumen;
}
