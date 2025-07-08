package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import apiTrackline.proyectoPTC.Entities.TransportistaEntity;
import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOTransportista;
import apiTrackline.proyectoPTC.Repositories.AduanaRepository;
import apiTrackline.proyectoPTC.Repositories.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AduanaService {
    @Autowired
    private AduanaRepository repo;

    @Autowired
    private TipoServicioRepository tipoServiciorepo;

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

    public String agregarAduana(DTOAduana dtoAduana){
        try
        {
            AduanaEntity entity = new AduanaEntity();
            entity.setDM(dtoAduana.getDM());
            entity.setPrimeraModalidad(dtoAduana.getPrimeraModalidad());
            entity.setSegundaModalidad(dtoAduana.getSegundaModalidad());
            entity.setDigitador(dtoAduana.getDigitador());
            entity.setTramitador(dtoAduana.getTramitador());

            // Buscar el tipo de servicio por el ID que viene en el DTO
            Optional<TipoServicioEntity> aduana = tipoServiciorepo.findById(dtoAduana.getIdTipoServicio());

            if (aduana.isPresent()) {
                entity.setIdTipoServicio(aduana.get()); // Asignar el tipo servicio si existe
                repo.save(entity); // Guardar aduana
                return "Transportista creado correctamente";
            } else {
                return "Error: ID de tipo servicio no encontrado";
            }
        }
        catch (Exception e)
        {
            return "ERROR: no se pudo agregar aduana " + e.getMessage();
        }
    }

    public String actualizarAduana(Long id, DTOAduana dto) {
        Optional<AduanaEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            AduanaEntity entity = optional.get();
            entity.setDM(dto.getDM());
            entity.setPrimeraModalidad(dto.getPrimeraModalidad());
            entity.setSegundaModalidad(dto.getSegundaModalidad());
            entity.setDigitador(dto.getDigitador());
            entity.setTramitador(dto.getTramitador());

            // Si también se quiere actualizar el tipo de servicio:
            if (dto.getIdTipoServicio() != null) {
                Optional<TipoServicioEntity> tipoServicio = tipoServiciorepo.findById(dto.getIdTipoServicio());
                tipoServicio.ifPresent(entity::setIdTipoServicio);
            }

            repo.save(entity);
            return "Información de la aduana actualizada correctamente";
        } else {
            return "Error: Transportista no encontrado";
        }
    }

    public String patchAduana(Long id, DTOAduana dto) {
        Optional<AduanaEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            AduanaEntity entity = optional.get();

            if (dto.getDM() != null) entity.setDigitador(dto.getDigitador());
            if (dto.getPrimeraModalidad() != null) entity.setPrimeraModalidad(dto.getPrimeraModalidad());
            if (dto.getSegundaModalidad() != null) entity.setSegundaModalidad(dto.getSegundaModalidad());
            if (dto.getDigitador() != null) entity.setDigitador(dto.getDigitador());
            if (dto.getTramitador() != null) entity.setTramitador(dto.getTramitador());

            if (dto.getIdTipoServicio() != null) {
                Optional<TipoServicioEntity> tipoServicio = tipoServiciorepo.findById(dto.getIdTipoServicio());
                tipoServicio.ifPresent(entity::setIdTipoServicio);
            }
            repo.save(entity);
            return "Aduana actualizada parcialmente.";
        }
        return "Transportista no encontrado.";
    }

    //ELiminar
    public String eliminarAduana(Long id) {
        Optional<AduanaEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Aduana eliminada correctamente";
        } else {
            return "Aduana no encontrada";
        }
    }
}
