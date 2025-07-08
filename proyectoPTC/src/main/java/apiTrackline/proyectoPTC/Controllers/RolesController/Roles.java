package apiTrackline.proyectoPTC.Controllers.RolesController;

import apiTrackline.proyectoPTC.Entities.RolesEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTORoles;
import apiTrackline.proyectoPTC.Services.RolesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiRoles")
public class Roles {
    @Autowired
    private RolesService service;

    //Obtener todos los roles
    //GET: localhost:8080/apiRoles/getRoles
    @GetMapping("/getRoles")
    public List<DTORoles> getRoles(){
        return service.obtenerRoles();
    }

    //Agregar roles
    //POST: localhost:8080/apiRoles/postRol
    @PostMapping("/postRol")
    public String postRoles(@Valid @RequestBody DTORoles dtoRoles){
        return service.agregarRoles(dtoRoles);
    }

    //Actualizar roles
    //PATCH: localhost:8080/apiRoles/patchRol/{id}
    @PatchMapping("/patchRol/{id}")
    public String patchRol(@PathVariable Long id, @Valid @RequestBody DTORoles dtoRoles){
        return service.actualizarRoles(dtoRoles, id);
    }

    //Eliminar roles
    //DELETE: localhost:8080/apiRoles/deleteRol/{id}
    @DeleteMapping("/deleteRol/{id}")
    public String deleteRoles(@PathVariable Long id){
        return service.eliminarRol(id);
    }
}
