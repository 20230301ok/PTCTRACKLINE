package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.ServicioTransporteEntity;
import apiTrackline.proyectoPTC.Entities.TransporteEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOTransporte;
import apiTrackline.proyectoPTC.Repositories.ServicioTransporteRepository;
import apiTrackline.proyectoPTC.Repositories.TransporteRepository;
import apiTrackline.proyectoPTC.Repositories.TransportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apiTrackline.proyectoPTC.Entities.TransportistaEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransporteService {


    @Autowired
    private TransporteRepository transporteRepo;

    @Autowired
    private TransportistaRepository transportistaRepo;

    @Autowired
    private ServicioTransporteRepository servicioTransporteRepo;

    public List<DTOTransporte> obtenerTransportes() {
        return transporteRepo.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DTOTransporte convertirADTO(TransporteEntity entity) {
        DTOTransporte dto = new DTOTransporte();
        dto.setIdTransporte(entity.getIdTransporte());

        // Datos del transportista
        if (entity.getTransportista() != null) {
            TransportistaEntity t = entity.getTransportista();
            dto.setIdTransportista(t.getIdTransportista());
            dto.setNombreTransportista(t.getNombre());
            dto.setApellidoTransportista(t.getApellido());
            dto.setTelefonoTransportista(t.getTelefono());
            dto.setCorreoTransportista(t.getCorreo());
            dto.setNitTransportista(t.getNit());
            dto.setIdUsuarioTransportista(t.getUsuarioT().getIdUsuario());
            dto.setNombreUsuario(t.getUsuarioT().getUsuario());
            dto.setContrasenia(t.getUsuarioT().getContrasenia());
        }

        // Datos del servicio transporte
        if (entity.getServicioTransporte() != null) {
            ServicioTransporteEntity s = entity.getServicioTransporte();
            dto.setIdServicioTransporte(s.getIdServicioTransporte());
            dto.setPlacaServicio(s.getPlaca());
            dto.setTarjetaCirculacionServicio(s.getTarjetaCirculacion());
            dto.setCapacidadServicio(s.getCapacidad());
        }

        return dto;
    }


    public String agregarTransporte(DTOTransporte dto) {
        try {
            TransporteEntity entity = new TransporteEntity();

            Optional<TransportistaEntity> transportista = transportistaRepo.findById(dto.getIdTransportista());
            Optional<ServicioTransporteEntity> servicio = servicioTransporteRepo.findById(dto.getIdServicioTransporte());

            if (transportista.isPresent() && servicio.isPresent()) {
                entity.setTransportista(transportista.get());
                entity.setServicioTransporte(servicio.get());

                transporteRepo.save(entity);
                return "Transporte agregado correctamente";
            } else {
                return "Error: Transportista o servicio no encontrado";
            }
        } catch (Exception e) {
            return "ERROR al agregar transporte: " + e.getMessage();
        }
    }
    public String actualizarTransporte(Long id, DTOTransporte dto) {
        Optional<TransporteEntity> optional = transporteRepo.findById(id);
        if (optional.isPresent()) {
            TransporteEntity entity = optional.get();

            // Validar si el nuevo idTransportista existe
            if (dto.getIdTransportista() != null) {
                Optional<TransportistaEntity> transportista = transportistaRepo.findById(dto.getIdTransportista());
                if (transportista.isPresent()) {
                    entity.setTransportista(transportista.get());
                } else {
                    return "Error: El ID del transportista ingresado no existe";
                }
            }

            // Validar si el nuevo idServicioTransporte existe
            if (dto.getIdServicioTransporte() != null) {
                Optional<ServicioTransporteEntity> servicio = servicioTransporteRepo.findById(dto.getIdServicioTransporte());
                if (servicio.isPresent()) {
                    entity.setServicioTransporte(servicio.get());
                } else {
                    return "Error: El ID del servicio de transporte ingresado no existe";
                }
            }

            transporteRepo.save(entity);
            return "Información del transporte actualizada correctamente";
        } else {
            return "Error: Transporte no encontrado";
        }
    }

    public String patchTransporte(Long id, DTOTransporte dto) {
        Optional<TransporteEntity> optional = transporteRepo.findById(id);
        if (optional.isPresent()) {
            TransporteEntity entity = optional.get();

            // Validar y actualizar transportista
            if (dto.getIdTransportista() != null) {
                Optional<TransportistaEntity> transportista = transportistaRepo.findById(dto.getIdTransportista());
                if (transportista.isPresent()) {
                    entity.setTransportista(transportista.get());
                } else {
                    return "Error: El ID de transportista ingresado no existe";
                }
            }

            // Validar y actualizar servicio transporte
            if (dto.getIdServicioTransporte() != null) {
                Optional<ServicioTransporteEntity> servicio = servicioTransporteRepo.findById(dto.getIdServicioTransporte());
                if (servicio.isPresent()) {
                    entity.setServicioTransporte(servicio.get());
                } else {
                    return "Error: El ID de servicio de transporte ingresado no existe";
                }
            }

            transporteRepo.save(entity);
            return "Transporte actualizado parcialmente.";
        }

        return "Transporte no encontrado.";
    }

    public String eliminarTransporte(Long id){
        Optional<TransporteEntity> optional = transporteRepo.findById(id);
        if (optional.isPresent()){
            transporteRepo.deleteById(id);
            return "Transporte eliminado correctamente";
        }
        else {
            return "No se encontró un transporte con id: " + id;
        }
    }
}
