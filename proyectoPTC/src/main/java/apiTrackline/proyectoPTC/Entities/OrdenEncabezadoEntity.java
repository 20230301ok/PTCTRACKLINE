package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Entity
@Table(name = "TB_ORDENENCABEZADO")
@Getter @Setter @ToString @EqualsAndHashCode
public class OrdenEncabezadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDORDENENCABEZADO")
    private long idOrdenEncabezado;

    @Column(name = "FECHA")
    private Date Fecha;

    @Column(name = "ENCARGADO")
    private String Encargado;

    @Column(name = "REFERENCIA")
    private String Referencia;

    @Column(name = "IMPORTADOR")
    private String Importador;

    @Column(name = "NIT")
    private String Nit;

    @Column(name = "REGISTROIVA")
    private String registroIva;

    @Column(name = "FACTURAA")
    private String facturaA;

    @Column(name = "ENCARGADO2")
    private String Encargado2;

    @Column(name = "NIT2")
    private String Nit2;

    @Column(name = "REGISTROIVA2")
    private String registroIva2;
}
