package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class RolesEntity {
    @Id
    @Column(name = "IDROL")
    private Long idRol;

    @Column(name = "ROL")
    private String rol;
}
