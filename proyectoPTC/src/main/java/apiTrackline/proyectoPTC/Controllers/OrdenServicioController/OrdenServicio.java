package apiTrackline.proyectoPTC.Controllers.OrdenServicioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Services.OrdenServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiOrdenServicio")
public class OrdenServicio {

    @Autowired
    private OrdenServicioService ordenServicioService;

    // Obtener todos
    //RUTA localhost:8080/apiOrdenServicio/data
    @GetMapping("/data")
    public List<DTOOrdenServicio> getAll() {
       return ordenServicioService.getData();
    }

    // Crear nueva orden servicioa
    @PostMapping("/create")
    public String create(@RequestBody DTOOrdenServicio dto) {
       return ordenServicioService.post(dto);
    }

    // Actualizar orden servicio completa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DTOOrdenServicio dto) {
        String respuesta = ordenServicioService.update(id, dto);
        if (respuesta.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    // Actualizar parcialmente (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<String> patch(@PathVariable Long id, @RequestBody DTOOrdenServicio dto) {
        // Para simplificar, reutilizamos el método update (o crea uno aparte en servicio que solo actualice no nulos)
        String respuesta = ordenServicioService.patch(id, dto);
        if (respuesta.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String respuesta = ordenServicioService.delete(id);
        if (respuesta.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }
}