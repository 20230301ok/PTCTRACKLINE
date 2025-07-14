package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.RecoleccionEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTORecoleccion;
import apiTrackline.proyectoPTC.Repositories.RecoleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RecoleccionService {

    @Autowired
    private RecoleccionRepository repo;

    //Método GET
    public List<DTORecoleccion> obtenerRecoleccion(){
        List<RecoleccionEntity> recoleccion = repo.findAll();
        List<DTORecoleccion> collect = recoleccion.stream()
                .map(this::convertirArecoleccionDTO)
                .collect(Collectors.toList());
        return collect;
    }

    private DTORecoleccion convertirArecoleccionDTO(RecoleccionEntity entity){
        DTORecoleccion dto = new DTORecoleccion();
        dto.setIdRecoleccion(entity.getIdRecoleccion());
        //Continuar





        return dto;
    }
}
