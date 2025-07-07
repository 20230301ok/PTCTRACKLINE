package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Repositories.TipoRepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoServicioService {
    @Autowired
    TipoRepositoryRepository repo;

    // Método público que usa el repositorio y convierte entidades a DTOs
    //Método HTTP GET (obtener datos)
    public List<DTOTipoServicio> obtenerTipoServicio() {
        List<TipoServicioEntity> servicios = repo.findAll();
        return servicios.stream()
                .map(this::convertirATipoServicioDTO)
                .collect(Collectors.toList());
    }

    //Convierte los datos del usuario a DTO
    private DTOTipoServicio convertirATipoServicioDTO(TipoServicioEntity servicio){
        DTOTipoServicio dto = new DTOTipoServicio();
        dto.setIdTipoServicio(servicio.getIdTipoServicio());
        dto.setTipoServicio(servicio.getTipoServicio());
        return dto;
    }

    public String agregarTipoServicio(DTOTipoServicio dto) {
        try {
            TipoServicioEntity ts = new TipoServicioEntity();
            ts.setTipoServicio(dto.getTipoServicio());

            repo.save(ts);
            return "Tipo de servicio agregado correctamente";
        } catch (Exception e) {
            return "Error al agregar el tipo de servicio: " + e.getMessage();
        }
    }

    public String actualizarTipoServicio(Long id, DTOTipoServicio dto) {
        Optional<TipoServicioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            TipoServicioEntity ts = optional.get();

            if (dto.getTipoServicio() != null) {
                ts.setTipoServicio(dto.getTipoServicio());
            }

            repo.save(ts);
            return "Tipo de servicio actualizado ";
        }
        return "Servicio no encontrado";
    }

    public String eliminarTipoServicio(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Tipo de servicio eliminado correctamente";
        }
        return "Tipo de servicio no encontrado";
    }

}
