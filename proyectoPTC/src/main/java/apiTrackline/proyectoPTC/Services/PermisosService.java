package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.PermisosEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOPermisos;
import apiTrackline.proyectoPTC.Repositories.PermisosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermisosService {
    @Autowired
    private PermisosRepository repo;

    // Método público que usa el repositorio y convierte entidades a DTOs
    //Método HTTP GET (obtener datos)
    public List<DTOPermisos> obtenerDatos(){
       List<PermisosEntity> permisos = repo.findAll();
       List<DTOPermisos> collect = permisos.stream()
               .map(this::convertirApermisosDTO)
               .collect(Collectors.toList());
       return collect;
    }

    private DTOPermisos convertirApermisosDTO(PermisosEntity entity){
        DTOPermisos dto = new DTOPermisos();
        dto.setIdPermiso(entity.getIdPermiso());
        dto.setNombrePermiso(entity.getNombrePermiso());
        return dto;
    }
}
