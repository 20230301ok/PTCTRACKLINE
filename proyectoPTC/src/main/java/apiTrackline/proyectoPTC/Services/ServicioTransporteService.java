package apiTrackline.proyectoPTC.Services;


import apiTrackline.proyectoPTC.Entities.ServicioTransporteEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOServicioTransporte;
import apiTrackline.proyectoPTC.Repositories.ServicioTransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioTransporteService {
    @Autowired
    private ServicioTransporteRepository repo;

    //MEtodo público que usa el repositorio y convierte entidades a DTOs
    //Metodo HTTP GET (obtener datos)
    public List<DTOServicioTransporte> getServicioTrans() {
        List<ServicioTransporteEntity> serviciotrans = repo.findAll();
        return serviciotrans.stream()
                .map(this::convertirAServicioTransDTO)
                .collect(Collectors.toList());
    }

    private DTOServicioTransporte convertirAServicioTransDTO(ServicioTransporteEntity serviciotrans){
        DTOServicioTransporte dto = new DTOServicioTransporte();
        dto.setIdServicioTransporte(serviciotrans.getIdServicioTransporte());
        dto.setPlaca(serviciotrans.getPlaca());
        dto.setTarjetaCirculacion(serviciotrans.getTarjetaCirculacion());
        dto.setCapacidad(serviciotrans.getCapacidad());
        return dto;
    }

    //Metodo HTTP post (insertar información)
    public String postServicioTrans(DTOServicioTransporte dtoServicioTransporte) {
        try {
            ServicioTransporteEntity serviciotrans = new ServicioTransporteEntity();
            serviciotrans.setIdServicioTransporte(dtoServicioTransporte.getIdServicioTransporte());
            serviciotrans.setTarjetaCirculacion(dtoServicioTransporte.getTarjetaCirculacion());
            serviciotrans.setPlaca(dtoServicioTransporte.getPlaca());
            serviciotrans.setCapacidad(dtoServicioTransporte.getCapacidad());
            repo.save(serviciotrans);
            return "Información de servicio de transporte agregada correctamente";
        } catch (Exception e) {
            return "Error al agregar la información de servicio de transporte: " + e.getMessage();
        }
    }


    //Metodo HTTP DELETE(eliminar) por id
    public String deleteServicioTrans(long id) {
        Optional<ServicioTransporteEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Información eliminada correctamente";
        } else {
            return "Información de el servicio de transporte no encontrada";
        }
    }

    //Metodo HTTP PUT(actualizar) por id
    public String updateServicioTrans(Long id, DTOServicioTransporte dtoServicioTransporte){
        Optional<ServicioTransporteEntity> optServicioTrans = repo.findById(id);
        if (optServicioTrans.isPresent()) {

            ServicioTransporteEntity serviciotrans = optServicioTrans.get();
            serviciotrans.setCapacidad(dtoServicioTransporte.getCapacidad());
            serviciotrans.setPlaca(dtoServicioTransporte.getPlaca());
            serviciotrans.setTarjetaCirculacion(dtoServicioTransporte.getTarjetaCirculacion());

            repo.save(serviciotrans);
            return "Servicio de transporte actualizado correctamente";
        } else {
            return "Servicio de transporte no encontrado con ID: " + (id);
        }
    }

    //Metodo HTTP PATCH(actualizar un solo campo) por id
    public String patchServicioTrans(Long id, DTOServicioTransporte dto) {
        Optional<ServicioTransporteEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            ServicioTransporteEntity serviciotrans = optional.get();


            if (dto.getCapacidad() != null) {
                serviciotrans.setCapacidad(dto.getCapacidad());
            }
            if (dto.getPlaca() != null) {
                serviciotrans.setPlaca(dto.getPlaca());
            }
            if (dto.getIdServicioTransporte() != null) {
                serviciotrans.setIdServicioTransporte(dto.getIdServicioTransporte());
            }
            if (dto.getTarjetaCirculacion() != null) {
                serviciotrans.setTarjetaCirculacion(dto.getTarjetaCirculacion());
            }
            repo.save(serviciotrans);
            return "Servicio de transporte actualizado parcialmente";
        }
        return "Servicio de transporte no encontrado";
    }
}
