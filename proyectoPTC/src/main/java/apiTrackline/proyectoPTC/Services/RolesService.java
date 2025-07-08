package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.RolesEntity;
import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTORoles;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolesService {
    @Autowired
    private RolesRepository repo;

    // Método público que usa el repositorio y convierte entidades a DTOs
    //Método HTTP GET (obtener datos)
    public List<DTORoles> obtenerRoles(){
        List<RolesEntity> roles = repo.findAll();
        List<DTORoles> collect = roles.stream()
                .map(this::convertirArolesDTO)
                .collect(Collectors.toList());
        return collect;
    }

    //Convierte los datos del usuario a DTO
    private DTOTipoServicio convertirATipoServicioDTO(TipoServicioEntity servicio){
        DTOTipoServicio dto = new DTOTipoServicio();
        dto.setIdTipoServicio(servicio.getIdTipoServicio());
        dto.setTipoServicio(servicio.getTipoServicio());
        return dto;
    }

    //Convertir a roles DTO
    private DTORoles convertirArolesDTO(RolesEntity rolesEntity){
        DTORoles dtoRoles = new DTORoles();
        dtoRoles.setIdRol(rolesEntity.getIdRol());
        dtoRoles.setRol(rolesEntity.getRol());
        return dtoRoles;
    }

    //Método POST
    public String agregarRoles(DTORoles dto) {
        try {
            RolesEntity rolesEntity = new RolesEntity();
            rolesEntity.setRol(dto.getRol());

            repo.save(rolesEntity);
            return "Roles agregado correctamente";
        } catch (Exception e) {
            return "Error al agregar el rol " + e.getMessage();
        }
    }

    //Método actualizar
    public String actualizarRoles(DTORoles dto, Long id){
        Optional<RolesEntity> optional = repo.findById(id);
        if(optional.isPresent()){
            RolesEntity roles = optional.get();
            if(dto.getRol() != null){
                roles.setRol(dto.getRol());
            }
            repo.save(roles);
            return "Rol actualizado ";
        }
        return "Rol no encontrado";
    }

    //Método eliminar
    public String eliminarRol(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Rol eliminado correctamente";
        }
        return "Rol no encontrado";
    }
}
