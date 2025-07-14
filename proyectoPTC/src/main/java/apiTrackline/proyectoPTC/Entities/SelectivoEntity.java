package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_SELECTIVO")
@Getter
@Setter
public class SelectivoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDSELECTIVO")
    private Long idSelectivo;

    @Column(name = "COLORSELECTIVO")
    private String colorSelectivo;
}
