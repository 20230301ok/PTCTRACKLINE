package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")
    private RolesEntity Rol;

    //Atributo extra para hacer una relación a la tabla transportista
    @OneToOne(mappedBy = "usuarioT", cascade = CascadeType.ALL)
    private TransportistaEntity transportistas;

    //Atributo extra para hacer una relación a la tabla Cliente
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ClientesEntity clientes ;

    @OneToOne(mappedBy = "usuarioEmpleado", cascade = CascadeType.ALL)
    private EmpleadosEntity empleados;

}
