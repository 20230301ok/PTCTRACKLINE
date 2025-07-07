package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.InfoEmbarqueEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOInfoEmbarque;
import apiTrackline.proyectoPTC.Repositories.InfoEmbarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfoEmbarqueService {

    @Autowired
    private InfoEmbarqueRepository repo;

    // Método público que usa el repositorio y convierte entidades a DTOs
    //Método HTTP GET (obtener datos)
    public List<DTOInfoEmbarque> obtenerInfoEmb() {
        List<InfoEmbarqueEntity> infoEmb = repo.findAll();
        return infoEmb.stream()
                .map(this::convertirAInfoEmbarqueDTO)
                .collect(Collectors.toList());
    }

    private DTOInfoEmbarque convertirAInfoEmbarqueDTO(InfoEmbarqueEntity infoEmb){
        DTOInfoEmbarque dto = new DTOInfoEmbarque();
        dto.setIdInfoEmbarque(infoEmb.getIdInfoEmbarque());
        dto.setFacturas(infoEmb.getFacturas());
        dto.setProveedorRef(infoEmb.getProveedorRef());
        dto.setBultos(infoEmb.getBultos());
        dto.setTipo(infoEmb.getTipo());
        dto.setKilos(infoEmb.getKilos());
        dto.setVolumen(infoEmb.getVolumen());
        return dto;
    }

    //Método HTTP post (insertar información)
    public String post(DTOInfoEmbarque dtoInfoEmbarque) {
        try {
            InfoEmbarqueEntity infoEmbarque = new InfoEmbarqueEntity();
            infoEmbarque.setIdInfoEmbarque(dtoInfoEmbarque.getIdInfoEmbarque());
            infoEmbarque.setFacturas(dtoInfoEmbarque.getFacturas());
            infoEmbarque.setProveedorRef(dtoInfoEmbarque.getProveedorRef());
            infoEmbarque.setBultos(dtoInfoEmbarque.getBultos());
            infoEmbarque.setTipo(dtoInfoEmbarque.getTipo());
            infoEmbarque.setKilos(dtoInfoEmbarque.getKilos());
            infoEmbarque.setVolumen(dtoInfoEmbarque.getVolumen());
            repo.save(infoEmbarque);
            return "Información del embarque agregada correctamente";
        } catch (Exception e) {
            return "Error al agregar la información del embarque: " + e.getMessage();
        }
    }


    //Método HTTP DELETE(eliminar) por id
    public String deleteInfoEmb(long id) {
        Optional<InfoEmbarqueEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Información eliminada correctamente";
        } else {
            return "Información del embarque no econtrada";
        }
    }

    //Método HTTP PUT(actualizar) por id
    public String updateInfoEmb(long id, DTOInfoEmbarque dtoInfoEmbarque){
        Optional<InfoEmbarqueEntity> optionalInfoEmb = repo.findById(id);
        if (optionalInfoEmb.isPresent()) {
            InfoEmbarqueEntity infoEmbarque = optionalInfoEmb.get();
            infoEmbarque.setFacturas(dtoInfoEmbarque.getFacturas());
            infoEmbarque.setProveedorRef(dtoInfoEmbarque.getProveedorRef());
            infoEmbarque.setBultos(dtoInfoEmbarque.getBultos());
            infoEmbarque.setTipo(dtoInfoEmbarque.getTipo());
            infoEmbarque.setKilos(dtoInfoEmbarque.getKilos());
            infoEmbarque.setVolumen(dtoInfoEmbarque.getVolumen());

            repo.save(infoEmbarque);
            return "Información del embarque actualizada correctamente";
        } else {
            return "Información del embarque no encontrada con ID: " + (id);
        }
    }

    //Método HTTP PATCH(actualizar un solo campo) por id
    public String patchInfEmb(Long id, DTOInfoEmbarque dto) {
        Optional<InfoEmbarqueEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            InfoEmbarqueEntity infoEmb = optional.get();


            if (dto.getFacturas() != null) {
                infoEmb.setFacturas(dto.getFacturas());
            }
            if (dto.getProveedorRef() != null) {
                infoEmb.setProveedorRef(dto.getProveedorRef());
            }
            if (dto.getBultos() != null) {
                infoEmb.setBultos(dto.getBultos());
            }
            if (dto.getTipo() != null) {
                infoEmb.setTipo(dto.getTipo());
            }
            if (dto.getKilos() != null) {
                infoEmb.setKilos(dto.getKilos());
            }
            if (dto.getVolumen() != null){
                infoEmb.setVolumen(dto.getVolumen());
            }
            repo.save(infoEmb);
            return "Información del embarque actualizado parcialmente";
        }
        return "Información de embarque no encontrada";
    }
}
