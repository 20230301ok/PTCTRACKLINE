package apiTrackline.proyectoPTC.Models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOSelectivo {
    private Long idSelectivo;

    private Boolean verde;
    private Boolean amarillo;
    private Boolean rojo;
}
