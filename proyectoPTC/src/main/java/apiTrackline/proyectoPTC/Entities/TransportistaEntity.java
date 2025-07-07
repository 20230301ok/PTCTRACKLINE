package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_TRANSPORTISTAS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransportistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_TRSP")
    @SequenceGenerator(name = "seq_id_TRSP", sequenceName = "seq_id_TRSP", allocationSize = 1)
    @Column(name = "IDTRANSPORTISTA")
    private Long idTransportista;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "NIT")
    private String nit;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    private UsuarioEntity usuario;
}
