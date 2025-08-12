package apiTrackline.proyectoPTC.Controllers.RecoleccionController;

import apiTrackline.proyectoPTC.Models.DTO.DTORecoleccion;
import apiTrackline.proyectoPTC.Services.RecoleccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiRecoleccion")
@CrossOrigin
public class Recoleccion {

    @Autowired
    private RecoleccionService service;

    //RUTA GET
    //localhost:8080/apiRecoleccion/obtenerDatos
    @GetMapping("/obtenerDatos")
    public List<DTORecoleccion> obtenerDatos(){
        return service.obtenerRecoleccion();
    }

    //RUTA POST
    //localhost:8080/apiRecoleccion/insertarRecoleccion
    @PostMapping("/insertarRecoleccion")
    public String insertarDatos(@Valid @RequestBody DTORecoleccion dto){
        return service.agregarRecoleccion(dto);
    }

    //RUTA PUT
    //localhost:8080/apiRecoleccion/actualizarInfo/id
    @PutMapping("/actualizarInfo/{id}")
    public String actualizarInfo(@PathVariable Long id, @Valid @RequestBody DTORecoleccion dto){
        return service.actualizarRecoleccion(id, dto);
    }

    //RUTA PATCH
    //localhost:8080/apiRecoleccion/actualizarParcialmenteReco/id
    @PatchMapping("/actualizarParcialmenteReco/{id}")
    public String actualizarParcialmente(@PathVariable Long id, @Valid @RequestBody DTORecoleccion dtoRecoleccion){
        return service.actualizarParcialmenteRecoleccion(id, dtoRecoleccion);
    }

    //RUTA PATCH
    //localhost:8080/apiRecoleccion/eliminarDatos/id
    @DeleteMapping("/eliminarDatos/{id}")
    public String eliminarDatos(@PathVariable Long id){
        return service.eliminarRecoleccion(id);
    }

}
