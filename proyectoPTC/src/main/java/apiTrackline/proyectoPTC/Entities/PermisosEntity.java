package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PERMISOS")
@Getter
@Setter
public class PermisosEntity {
    @Id
    @Column(name = "IDPERMISO")
    private Long idPermiso;

    @Column(name = "NOMBREPERMISO")
    private String nombrePermiso;
}
