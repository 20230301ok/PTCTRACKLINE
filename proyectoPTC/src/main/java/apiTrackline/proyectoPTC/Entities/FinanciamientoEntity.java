package apiTrackline.proyectoPTC.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_FINANCIAMIENTOS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FinanciamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDFINANCIAMIENTO")
    private Long idFinanciamiento;

    @ManyToOne
    @JoinColumn(name = "IDTIPOFINANCIAMIENTO", referencedColumnName = "IDTIPOFINANCIAMIENTO")
    private TipoFinanciamientosEntity tipoFinanciamiento;

    @Column(name = "MONTO")
    private Long monto;
}