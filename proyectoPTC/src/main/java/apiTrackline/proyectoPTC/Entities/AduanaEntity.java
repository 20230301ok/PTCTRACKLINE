package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "TB_ADUANA")
public class AduanaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si se autogenera en la BD
    @Column(name = "IDADUANA")
    private Long idAduana;

    //LLave foránea hacia la tabla tb_tipoServicio
    @ManyToOne
    @JoinColumn(name = "IDTIPOSERVICIO", referencedColumnName = "IDTIPOSERVICIO")
    private TipoServicioEntity tipoServicio;

    @Column(name = "DM")
    private String DM;

    @Column(name = "PRIMERAMODALIDAD")
    private String primeraModalidad;

    @Column(name = "SEGUNDAMODALIDAD")
    private String segundaModalidad;

    @Column(name = "DIGITADOR")
    private String digitador;

    @Column(name = "TRAMITADOR")
    private String tramitador;
}
