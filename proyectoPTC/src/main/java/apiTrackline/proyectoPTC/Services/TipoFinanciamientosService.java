package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.TipoFinanciamientosEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoFinanciamientos;
import apiTrackline.proyectoPTC.Repositories.TipoFinanciamientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoFinanciamientosService {
    @Autowired
    TipoFinanciamientosRepository repo;

    // Metodo público que usa el repositorio y convierte entidades a DTOs
    //Metodo HTTP GET (obtener datos)
    public List<DTOTipoFinanciamientos> obtenerTipoFinanciamiento() {
        List<TipoFinanciamientosEntity> financiamientos = repo.findAll();
        return financiamientos.stream()
                .map(this::convertirATipoFinanciamientoDTO)
                .collect(Collectors.toList());
    }

    //Convierte los datos del usuario a DTO
    private DTOTipoFinanciamientos convertirATipoFinanciamientoDTO(TipoFinanciamientosEntity financiamientos){
        DTOTipoFinanciamientos dto = new DTOTipoFinanciamientos();
        dto.setIdTipoFinanciamiento(financiamientos.getIdTipoFinanciamiento());
        dto.setNombre(financiamientos.getNombre());
        return dto;
    }
}
