package apiTrackline.proyectoPTC.Controllers.TransporteController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTransporte;
import apiTrackline.proyectoPTC.Services.TransporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTransporte")
public class Transporte {

    @Autowired
    private TransporteService service;

    //METODO GET
    //RUTA: localhost:8080/apiTransporte/listar
    @GetMapping("/listar")
    public List<DTOTransporte> listarTransportes() {
        return service.obtenerTransportes();
    }

    //METODO POST
    //RUTA: localhost:8080/apiTransporte/agregar
    @PostMapping("/agregar")
    public String agregar(@Validated(DTOTransporte.OnCreate.class) @RequestBody DTOTransporte dto) {
        return service.agregarTransporte(dto);
    }

    //METODO PUT
    //RUTA: localhost:8080/apiTransporte/actualizar/id
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @Validated(DTOTransporte.OnUpdate.class) @RequestBody DTOTransporte dto) {
        return service.actualizarTransporte(id, dto);
    }

    //METODO PATCH
    //RUTA: localhost:8080/apiTransporte/actualizarParcial/id
    @PatchMapping("/actualizarParcial/{id}")
    public String patch(@PathVariable Long id,@Valid @RequestBody DTOTransporte dto) {
        return service.patchTransporte(id, dto);
    }

    //METODO DELETE
    //RUTA: localhost:8080/apiTransporte/eliminar/id
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        return service.eliminarTransporte(id);
    }
}
