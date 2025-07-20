package apiTrackline.proyectoPTC.Controllers.AduanaController;

import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Services.AduanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiAduana")
public class Aduana {
    @Autowired
    private AduanaService service;

    //MÉTODO GET
    //RUTA: localhost:8080/apiAduana/datosAduana
    @GetMapping("/datosAduana")
    //El metodo pide una lista de tipo dto porque en el front end solo se puede mostrar un DTO
    public List<DTOAduana> getAduana() {
        return service.obtenerAduana();
    }

    //MÉTODO POST
    //RUTA: localhost:8080/apiAduana/agregarAduana
    @PostMapping("/agregarAduana")
    //El metodo pide un string porque se muestra un mensaje de error o de confirmación de datos agregados
    //Se pasan los valores de DTO al método del service
    public String agregarAduana(@Validated(DTOAduana.OnCreate.class) @RequestBody DTOAduana dtoAduana){
        return service.agregarAduana(dtoAduana);
    }

    //MÉTODO PUT
    //RUTA: localhost:8080/apiAduana/actualizarAduana/{id}
    @PutMapping("/actualizarAduana/{id}")
    public String actualizarAduana(@PathVariable Long id, @Validated(DTOAduana.OnUpdate.class) @RequestBody DTOAduana dtoAduana){
        return service.actualizarAduana(id, dtoAduana);
    }

    //MÉTODO PATCH
    //RUTA: Localhost:8080/apiAduana/actualizarParcialmente/{id}
    @PatchMapping("/actualizarParcialmente/{id}")
    public String actualizarParcialmenteAduana(@PathVariable Long id, @Validated(DTOAduana.OnPatch.class) @RequestBody DTOAduana dtoAduana){
        return service.patchAduana(id, dtoAduana);
    }

    //MÉTODO DELETE
    //RUTA: localhost:8080/apiAduana/eliminarAduana/{id}
    @DeleteMapping("/eliminarAduana/{id}")
    public String eliminarAduana(@PathVariable Long id){
        return service.eliminarAduana(id);
    }
}
