package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_SELECTIVO")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SelectivoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "idSelectivo")
    private Long idSelectivo;

    @Column(name = "verde")
    private Boolean verde;

    @Column(name = "amarillo")
    private Boolean amarillo;

    @Column(name = "rojo")
    private Boolean rojo;
}
