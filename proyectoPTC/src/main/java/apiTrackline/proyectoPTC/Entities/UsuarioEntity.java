package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_USUARIOS")
@Getter @Setter @ToString @EqualsAndHashCode
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDUSUARIO")
    private Long idUsuario;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "CONTRASENIA")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")
    private RolesEntity idRol;
}
