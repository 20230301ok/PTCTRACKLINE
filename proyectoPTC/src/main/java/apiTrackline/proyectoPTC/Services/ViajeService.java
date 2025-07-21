package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOViaje;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository repo;

    @Autowired
    private OrdenServicioRepository ordenServicioRepo;

    @Autowired
    private TransporteRepository transporteRepo;

    // Obtener todos los viajes
    public List<DTOViaje> getData() {
        return repo.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Conversión de Entity a DTO
    private DTOViaje convertirADTO(ViajeEntity entity) {
        DTOViaje dto = new DTOViaje();
        dto.setIdViaje(entity.getIdViaje());

        if (entity.getOrdenServicio() != null) {
            dto.setIdOrdenServicio(entity.getOrdenServicio().getIdOrdenServicio());
        }

        if (entity.getIdTransporte() != null) {
            dto.setIdTransporte(entity.getIdTransporte().getIdTransporte());
        }

        return dto;
    }

    // Crear viaje
    public String post(DTOViaje dto) {
        try {
            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            Optional<TransporteEntity> transporte = transporteRepo.findById(dto.getIdTransporte());

            if (orden.isEmpty()) return "Error: ID de orden de servicio no válido";
            if (transporte.isEmpty()) return "Error: ID de transporte no válido";

            ViajeEntity entity = new ViajeEntity();
            entity.setOrdenServicio(orden.get());
            entity.setIdTransporte(transporte.get());

            repo.save(entity);
            return "Viaje creado correctamente";
        } catch (Exception e) {
            return "Error al crear el viaje: " + e.getMessage();
        }
    }

    // Actualizar completamente
    public String update(Long id, DTOViaje dto) {
        try {
            Optional<ViajeEntity> optional = repo.findById(id);
            if (optional.isEmpty()) return "Error: Viaje no encontrado";

            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            Optional<TransporteEntity> transporte = transporteRepo.findById(dto.getIdTransporte());

            if (orden.isEmpty()) return "Error: ID de orden de servicio no válido";
            if (transporte.isEmpty()) return "Error: ID de transporte no válido";

            ViajeEntity entity = optional.get();
            entity.setOrdenServicio(orden.get());
            entity.setIdTransporte(transporte.get());

            repo.save(entity);
            return "Viaje actualizado correctamente";
        } catch (Exception e) {
            return "Error al actualizar el viaje: " + e.getMessage();
        }
    }

    // Actualización parcial
    public String patch(Long id, DTOViaje dto) {
        try {
            Optional<ViajeEntity> optional = repo.findById(id);
            if (optional.isEmpty()) return "Error: Viaje no encontrado";

            ViajeEntity entity = optional.get();

            if (dto.getIdOrdenServicio() != null) {
                ordenServicioRepo.findById(dto.getIdOrdenServicio())
                        .ifPresent(entity::setOrdenServicio);
            }

            if (dto.getIdTransporte() != null) {
                transporteRepo.findById(dto.getIdTransporte())
                        .ifPresent(entity::setIdTransporte);
            }

            repo.save(entity);
            return "Viaje actualizado parcialmente";
        } catch (Exception e) {
            return "Error al hacer patch del viaje: " + e.getMessage();
        }
    }

    // Eliminar viaje
    public String delete(Long id) {
        try {
            if (!repo.existsById(id)) {
                return "Error: Viaje no encontrado";
            }
            repo.deleteById(id);
            return "Viaje eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar el viaje: " + e.getMessage();
        }
    }
}
