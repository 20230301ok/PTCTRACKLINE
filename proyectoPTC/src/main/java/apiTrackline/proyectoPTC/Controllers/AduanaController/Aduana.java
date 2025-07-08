package apiTrackline.proyectoPTC.Controllers.AduanaController;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Services.AduanaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DTOAduana> getAduana() {
        return service.obtenerAduana();
    }

    //MÉTODO POST
    //RUTA: localhost8080/apiAduana/datosAduana
    @PostMapping("/agregarAduana")
    public String agregarAduana(@Valid @RequestBody DTOAduana dtoAduana){
        return service.agregarAduana(dtoAduana);
    }

    //MÉTODO PUT
    //RUTA: localhost:8080/apiAduana/actualizarAduana/{id}
    @PutMapping("/actualizarAduana/{id}")
    public String actualizarAduana(@PathVariable Long id, @Valid @RequestBody DTOAduana dtoAduana){
        return service.actualizarAduana(id, dtoAduana);
    }

    //MÉTODO PATCH
    //RUTA: Localhost:8080/apiAduana/actualizarParcialmente/{id}
    @PatchMapping("/actualizarParcialmente/{id}")
    public String actualizarParcialmenteAduana(@PathVariable Long id,@Valid @RequestBody DTOAduana dtoAduana){
        return service.patchAduana(id, dtoAduana);
    }

    //MÉTODO DELETE
    //RUTA: localhost:8080/apiAduana/eliminarAduana/{id}
    @DeleteMapping("/eliminarAduana/{id}")
    public String eliminarAduana(@PathVariable Long id){
        return service.eliminarAduana(id);
    }
}
