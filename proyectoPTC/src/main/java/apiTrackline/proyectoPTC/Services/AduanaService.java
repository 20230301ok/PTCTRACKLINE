package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Repositories.AduanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AduanaService {
    @Autowired
    AduanaRepository repo;

    public List<DTOAduana> obtenerAduana(){
        List<AduanaEntity> aduana = repo.findAll();
        List<DTOAduana> collect = aduana.stream()
                .map(this::convertirAAduanaDTO)
                .collect(Collectors.toList());
        return collect;
    }

    public DTOAduana convertirAAduanaDTO(AduanaEntity aduanaEntity, TipoServicioEntity tipoServicioEntity){
        DTOAduana dto = new DTOAduana();
        dto.setIdAduana(aduanaEntity.getIdAduana());
        dto.setIdTipoServicio(tipoServicioEntity.getIdTipoServicio());
        dto.setDM(aduanaEntity.getDM());
        dto.setPrimeraModalidad(aduanaEntity.getPrimeraModalidad());
        dto.setSegundaModalidad(aduanaEntity.getSegundaModalidad());
        dto.setDigitador(aduanaEntity.getDigitador());
        dto.setTramitador(aduanaEntity.getTramitador());

        return dto;
    }
}
