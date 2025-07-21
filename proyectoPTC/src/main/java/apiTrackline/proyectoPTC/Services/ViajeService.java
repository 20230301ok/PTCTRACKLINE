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

    public List<DTOViaje> getData() {
        List<ViajeEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOViaje convertirADTO(ViajeEntity v) {
        DTOViaje dto = new DTOViaje();
        dto.setIdViaje(v.getIdViaje());

        if (v.getIdOrdenServicio() != null) {
            dto.setIdOrdenServicio(v.getIdOrdenServicio().getIdOrdenServicio());
        }

        if (v.getIdTransporte() != null) {
            dto.setIdTransporte(v.getIdTransporte().getIdTransporte());
        }

        return dto;
    }

    public String post(DTOViaje dto) {
        try {
            ViajeEntity v = new ViajeEntity();

            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            Optional<TransporteEntity> transporte = transporteRepo.findById(dto.getIdTransporte());

            if (orden.isEmpty()) return "Error: ID de orden de servicio no válido";
            if (transporte.isEmpty()) return "Error: ID de transporte no válido";

            v.setIdOrdenServicio(orden.get());
            v.setIdTransporte(transporte.get());

            repo.save(v);
            return "Viaje creado correctamente";
        } catch (Exception e) {
            return "Error al crear viaje: " + e.getMessage();
        }
    }

    public String update(Long id, DTOViaje dto) {
        Optional<ViajeEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            ViajeEntity v = optional.get();

            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            Optional<TransporteEntity> transporte = transporteRepo.findById(dto.getIdTransporte());

            orden.ifPresent(v::setIdOrdenServicio);
            transporte.ifPresent(v::setIdTransporte);

            repo.save(v);
            return "Información del viaje actualizada correctamente";
        } else {
            return "Error: Viaje no encontrado";
        }
    }

    public String patch(Long id, DTOViaje dto) {
        Optional<ViajeEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            ViajeEntity v = optional.get();

            if (dto.getIdOrdenServicio() != null) {
                ordenServicioRepo.findById(dto.getIdOrdenServicio()).ifPresent(v::setIdOrdenServicio);
            }
            if (dto.getIdTransporte() != null) {
                transporteRepo.findById(dto.getIdTransporte()).ifPresent(v::setIdTransporte);
            }

            repo.save(v);
            return "Viaje actualizado parcialmente";
        }
        return "Viaje no encontrado";
    }

    public String delete(Long id) {
        Optional<ViajeEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Viaje eliminado correctamente";
        } else {
            return "Viaje no encontrado";
        }
    }
}
