package apiTrackline.proyectoPTC.Controllers.TrackingController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTracking;
import apiTrackline.proyectoPTC.Services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTracking")
@CrossOrigin
public class Tracking {

    @Autowired
    private TrackingService service;

    // GET: Obtener todos los trackings
    //ruta localhost:8080/apiTracking/listar
    @GetMapping("/listar")
    public List<DTOTracking> listar() {
        return service.getAll();
    }

    // POST: Crear tracking con parámetros en JSON
    //ruta localhost:8080/apiTracking/crear
    @PostMapping("/crear")
    public String crearTracking(@Validated(DTOTracking.OnCreate.class) @RequestBody DTOTracking dto) {
        return service.create(dto);
    }

    //ruta localhost:8080/apiTracking/eliminar/id
    @DeleteMapping("/eliminar/{id}")
    public String eliminarTracking(@PathVariable Long id) {
        return service.delete(id);
    }

    //ruta localhost:8080/apiTracking/actualizarparcial/id
    @PatchMapping("/actualizarparcial/{id}")
    public String patchTracking(@PathVariable Long id,@Validated(DTOTracking.OnPatch.class) @RequestBody DTOTracking dto) {
        return service.patch(id, dto);
    }

    //ruta localhost:8080/apiTracking/actualizar/id
    @PutMapping("/actualizar/{id}")
    public String putUpdateTracking(@PathVariable Long id,@Validated(DTOTracking.OnUpdate.class) @RequestBody DTOTracking dto) {
        return service.putUpdate(id, dto);
    }
}
