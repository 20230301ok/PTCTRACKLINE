package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOTracking;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TrackingService {

    @Autowired
    private TrackingRepository repo;

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private EstadosRepository estadosRepo;

    public List<DTOTracking> getAll() {
        List<TrackingEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOTracking convertirADTO(TrackingEntity entity) {
        DTOTracking dto = new DTOTracking();

        dto.setIdTracking(entity.getIdTracking());
        dto.setIdViaje(entity.getIdViaje());
        dto.setIdEstado(entity.getIdEstado());

        // Campos Tracking
        dto.setHoraEstimadaPartida(entity.getHoraEstimadaPartida());
        dto.setHoraEstimadaLlegada(entity.getHoraEstimadaLlegada());
        dto.setHoraSalida(entity.getHoraSalida());
        dto.setHoraLlegada(entity.getHoraLlegada());
        dto.setLugarPartida(entity.getLugarPartida());
        dto.setLugarLlegada(entity.getLugarLlegada());
        dto.setIdEstado(entity.getIdEstado());

        // Relación Viaje y desde ahí OrdenServicio y Transporte
        if (entity.getViaje() != null) {
            ViajeEntity viaje = entity.getViaje();
            dto.setIdOrdenServicio(viaje.getOrdenServicio() != null ? viaje.getOrdenServicio().getIdOrdenServicio() : null);

            if (viaje.getOrdenServicio() != null) {
                var orden = viaje.getOrdenServicio();

                dto.setClienteNIT(orden.getClienteNIT());

                if (orden.getCliente() != null) {
                    dto.setNombreCliente(orden.getCliente().getNombre());
                    dto.setApellidoCliente(orden.getCliente().getApellido());
                    dto.setTelefonoCliente(orden.getCliente().getTelefono());
                    dto.setCorreoCliente(orden.getCliente().getCorreo());
                    dto.setCodEmpresaCliente(orden.getCliente().getCodEmpresa());

                    if (orden.getCliente().getUsuario() != null) {
                        dto.setIdUsuarioCliente(orden.getCliente().getUsuario().getIdUsuario());
                        dto.setNombreUsuarioCliente(orden.getCliente().getUsuario().getUsuario());
                    }
                }
            }

            // Transporte desde viaje
            if (viaje.getTransporte() != null) {
                dto.setIdTransporte(viaje.getTransporte().getIdTransporte());

                if (viaje.getTransporte().getServicioTransporte() != null) {
                    var servicio = viaje.getTransporte().getServicioTransporte();
                    dto.setPlacaServicio(servicio.getPlaca());
                    dto.setTarjetaCirculacionServicio(servicio.getTarjetaCirculacion());
                    dto.setCapacidadServicio(servicio.getCapacidad());
                }
            }
        }

        return dto;
    }


    public String create(DTOTracking dto) {
        try {
            Optional<ViajeEntity> viajeOpt = viajeRepo.findById(dto.getIdViaje());
            if (viajeOpt.isEmpty()) return "Error: Viaje no encontrado";

            ViajeEntity viaje = viajeOpt.get();
            if (viaje.getOrdenServicio() == null)
                return "Error: El viaje no tiene una orden de servicio asociada";

            Optional<EstadosEntity> estadoOpt = estadosRepo.findById(dto.getIdEstado());
            if (estadoOpt.isEmpty()) return "Error: Estado no encontrado";

            TrackingEntity entity = new TrackingEntity();
            entity.setViaje(viaje);
            entity.setIdViaje(dto.getIdViaje());
            entity.setEstado(estadoOpt.get());
            entity.setIdEstado(dto.getIdEstado());

            entity.setHoraEstimadaPartida(dto.getHoraEstimadaPartida());
            entity.setHoraEstimadaLlegada(dto.getHoraEstimadaLlegada());
            entity.setHoraSalida(dto.getHoraSalida());
            entity.setHoraLlegada(dto.getHoraLlegada());
            entity.setLugarPartida(dto.getLugarPartida());
            entity.setLugarLlegada(dto.getLugarLlegada());

            repo.save(entity);
            return "Tracking creado correctamente";
        } catch (Exception e) {
            return "Error al crear Tracking: " + e.getMessage();
        }
    }


    public String putUpdate(Long id, DTOTracking dto) {
        try {
            Optional<TrackingEntity> optional = repo.findById(id);
            if (optional.isEmpty()) return "Error: Tracking no encontrado";

            if (dto.getIdViaje() == null || dto.getIdEstado() == null)
                return "Error: PUT requiere idViaje y idEstado";

            Optional<ViajeEntity> viajeOpt = viajeRepo.findById(dto.getIdViaje());
            if (viajeOpt.isEmpty()) return "Error: Viaje no encontrado";

            if (viajeOpt.get().getOrdenServicio() == null)
                return "Error: El viaje no tiene una orden de servicio asociada";

            Optional<EstadosEntity> estadoOpt = estadosRepo.findById(dto.getIdEstado());
            if (estadoOpt.isEmpty()) return "Error: Estado no encontrado";

            TrackingEntity entity = optional.get();
            entity.setViaje(viajeOpt.get());
            entity.setIdViaje(dto.getIdViaje());
            entity.setEstado(estadoOpt.get());
            entity.setIdEstado(dto.getIdEstado());

            entity.setHoraEstimadaPartida(dto.getHoraEstimadaPartida());
            entity.setHoraEstimadaLlegada(dto.getHoraEstimadaLlegada());
            entity.setHoraSalida(dto.getHoraSalida());
            entity.setHoraLlegada(dto.getHoraLlegada());
            entity.setLugarPartida(dto.getLugarPartida());
            entity.setLugarLlegada(dto.getLugarLlegada());

            repo.save(entity);
            return "Tracking actualizado correctamente";
        } catch (Exception e) {
            return "Error al actualizar Tracking: " + e.getMessage();
        }
    }


    public String delete(Long id) {
        try {
            if (!repo.existsById(id)) return "Error: Tracking no encontrado";
            repo.deleteById(id);
            return "Tracking eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar Tracking: " + e.getMessage();
        }
    }

    public String patch(Long id, DTOTracking dto) {
        try {
            Optional<TrackingEntity> optional = repo.findById(id);
            if (optional.isEmpty()) return "Error: Tracking no encontrado";

            TrackingEntity entity = optional.get();

            if (dto.getIdViaje() != null) {
                Optional<ViajeEntity> viajeOpt = viajeRepo.findById(dto.getIdViaje());
                if (viajeOpt.isEmpty()) return "Error: Viaje no encontrado";

                if (viajeOpt.get().getOrdenServicio() == null)
                    return "Error: El viaje no tiene una orden de servicio asociada";

                entity.setViaje(viajeOpt.get());
                entity.setIdViaje(dto.getIdViaje());
            }

            if (dto.getIdEstado() != null) {
                Optional<EstadosEntity> estadoOpt = estadosRepo.findById(dto.getIdEstado());
                if (estadoOpt.isEmpty()) return "Error: Estado no encontrado";

                entity.setEstado(estadoOpt.get());
                entity.setIdEstado(dto.getIdEstado());
            }

            if (dto.getHoraEstimadaPartida() != null) entity.setHoraEstimadaPartida(dto.getHoraEstimadaPartida());
            if (dto.getHoraEstimadaLlegada() != null) entity.setHoraEstimadaLlegada(dto.getHoraEstimadaLlegada());
            if (dto.getHoraSalida() != null) entity.setHoraSalida(dto.getHoraSalida());
            if (dto.getHoraLlegada() != null) entity.setHoraLlegada(dto.getHoraLlegada());
            if (dto.getLugarPartida() != null) entity.setLugarPartida(dto.getLugarPartida());
            if (dto.getLugarLlegada() != null) entity.setLugarLlegada(dto.getLugarLlegada());

            repo.save(entity);
            return "Tracking actualizado parcialmente";
        } catch (Exception e) {
            return "Error al actualizar Tracking: " + e.getMessage();
        }
    }

}
