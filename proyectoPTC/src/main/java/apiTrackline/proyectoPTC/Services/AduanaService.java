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

    // Método que convierte una entidad AduanaEntity a un objeto DTOAduana
    public DTOAduana convertirAAduanaDTO(AduanaEntity aduanaEntity) {
        // Se crea un nuevo objeto DTO (Data Transfer Object) para transportar los datos
        DTOAduana dto = new DTOAduana();
        // Se asigna el ID de la aduana directamente desde la entidad
        dto.setIdAduana(aduanaEntity.getIdAduana());
        // Verificamos si el objeto relacionado "TipoServicio" no es nulo (evitamos NullPointerException)
        if (aduanaEntity.getIdTipoServicio() != null) {
            // Se obtiene el ID del tipo de servicio relacionado
            dto.setIdTipoServicio(aduanaEntity.getIdTipoServicio().getIdTipoServicio());

            // También se obtiene el nombre del tipo de servicio
            dto.setNombreTipoServicio(aduanaEntity.getIdTipoServicio().getTipoServicio());
        } else {
            // Si es nulo, se dejan en null ambos campos
            dto.setIdTipoServicio(null);
            dto.setNombreTipoServicio(null);
        }

        // Se copian los demás atributos directamente desde la entidad
        dto.setDM(aduanaEntity.getDM());
        dto.setPrimeraModalidad(aduanaEntity.getPrimeraModalidad());
        dto.setSegundaModalidad(aduanaEntity.getSegundaModalidad());
        dto.setDigitador(aduanaEntity.getDigitador());
        dto.setTramitador(aduanaEntity.getTramitador());

        // Se retorna el DTO ya completo
        return dto;
    }

}
