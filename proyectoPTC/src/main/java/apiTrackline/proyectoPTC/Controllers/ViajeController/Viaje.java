package apiTrackline.proyectoPTC.Controllers.ViajeController;

import apiTrackline.proyectoPTC.Models.DTO.DTOViaje;
import apiTrackline.proyectoPTC.Services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiViaje")
@CrossOrigin
public class Viaje {
    @Autowired
    private ViajeService service;

    // GET: obtiene todos los viajes
    //RUTA GET: localhost:8080/apiViaje/obtener
    @GetMapping("/obtener")
    public List<DTOViaje> obtenerTodos() {
        return service.getAll();
    }

    // POST: crear un nuevo viaje con JSON en el body
    //RUTA localhost:8080/apiViaje/crear
    @PostMapping("/crear")
    public String crearViaje(@Validated(DTOViaje.OnCreate.class) @RequestBody DTOViaje dto) {
        return service.create(dto.getIdOrdenServicio(), dto.getIdTransporteViaje());

    }

    // DELETE: eliminar un viaje por ID
    // localhost:8080/apiViaje/eliminar/id
    @DeleteMapping("/eliminar/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        return service.delete(id);
    }

    // PATCH: actualizar parcialmente un viaje
    // localhost:8080/apiViaje/actualizarparcial/id
    @PatchMapping("/actualizarparcial/{id}")
    public String actualizarViaje(@PathVariable Long id, @Validated(DTOViaje.OnPatch.class)
                                  @RequestBody DTOViaje dto) {
        return service.patch(id, dto.getIdOrdenServicio(), dto.getIdTransporteViaje());
    }

    // PUT: reemplazar completamente un viaje
    // localhost:8080/apiViaje/actualizar/id
    @PutMapping("/actualizar/{id}")
    public String reemplazarViaje(@PathVariable Long id, @Validated(DTOViaje.OnUpdate.class)
                                  @RequestBody DTOViaje dto) {
        return service.putUpdate(id, dto.getIdOrdenServicio(), dto.getIdTransporteViaje());
    }
}
