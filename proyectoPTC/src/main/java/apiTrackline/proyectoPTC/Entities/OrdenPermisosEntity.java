package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ORDENPERMISOS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrdenPermisosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDORDENPERMISOS")
    private Long idOrdenPermisos;

    @ManyToOne
    @JoinColumn(name = "IDPERMISO", referencedColumnName = "IDPERMISO")
    private PermisosEntity idPermiso;
    
    @Column(name = "MARCADO")
    private Boolean marcado;
}
