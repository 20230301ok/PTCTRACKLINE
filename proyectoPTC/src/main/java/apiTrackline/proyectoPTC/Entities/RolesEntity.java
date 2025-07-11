package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "TB_ROLES")
public class RolesEntity {
    @Id
    @Column(name = "IDROL")
    private Long idRol;

    @Column(name = "ROL")
    private String rol;
}
