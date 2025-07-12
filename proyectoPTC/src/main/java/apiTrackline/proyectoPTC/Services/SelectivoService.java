package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.SelectivoEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOSelectivo;
import apiTrackline.proyectoPTC.Repositories.SelectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectivoService {
    @Autowired
    private SelectivoRepository repo;

    public List<DTOSelectivo> obtenerSelectivos(){
        List<SelectivoEntity> selectivo = repo.findAll();
        List<DTOSelectivo> collect = selectivo.stream()
                .map(this::convertirADTOselectivo)
                .collect(Collectors.toList());
        return collect;
    }

    private DTOSelectivo convertirADTOselectivo(SelectivoEntity entity){
        DTOSelectivo dto = new DTOSelectivo();
        dto.setIdSelectivo(entity.getIdSelectivo());
        dto.setColorSelectivo(entity.getColorSelectivo());
        return dto;
    }
}
