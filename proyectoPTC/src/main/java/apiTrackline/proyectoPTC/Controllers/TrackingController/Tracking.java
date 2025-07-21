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
public class Tracking {

    @Autowired
    private TrackingService service;

    // RUTA GET localhost:8080/apiTracking/list
    @GetMapping("/list")
    public List<DTOTracking> list() {
        return service.getAll();
    }

    // RUTA GET localhost:8080/apiTracking/getById/{id}
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DTOTracking result = service.getById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(404).body("Error: No se encontró el tracking con ID " + id);
    }

    // RUTA POST localhost:8080/apiTracking/agregarTracking
    @PostMapping("/agregarTracking")
    public ResponseEntity<DTOTracking> post(@Validated(DTOTracking.OnCreate.class) @RequestBody DTOTracking dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    // RUTA PUT localhost:8080/apiTracking/actualizarTracking/{id}
    @PutMapping("/actualizarTracking/{id}")
    public ResponseEntity<?> update(@Validated(DTOTracking.OnUpdate.class) @RequestBody DTOTracking dto, @PathVariable Long id) {
        Object result = service.update(dto, id);
        if (result instanceof DTOTracking) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(404).body(result);
    }

    // RUTA PATCH localhost:8080/apiTracking/actualizarParcialmente/{id}
    @PatchMapping("/actualizarParcialmente/{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @Validated(DTOTracking.OnPatch.class) @RequestBody DTOTracking dto) {
        Object result = service.patch(dto, id);
        if (result instanceof DTOTracking) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(404).body(result);
    }

    // RUTA DELETE localhost:8080/apiTracking/eliminarTracking/{id}
    @DeleteMapping("/eliminarTracking/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String result = service.delete(id);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
