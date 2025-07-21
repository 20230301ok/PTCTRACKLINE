package apiTrackline.proyectoPTC.Controllers.OrdenServicioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Services.OrdenServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    //RUTA localhost:8080/apiOrdenServicio/data
    @PostMapping("/create")
    public String create(@Validated(DTOOrdenServicio.OnCreate.class) @RequestBody DTOOrdenServicio dto) {
       return ordenServicioService.post(dto);
    }

    // Actualizar orden servicio completa (PUT)
    //RUTA localhost:8080/apiOrdenServicio/put/id
    @PutMapping("/put/{id}")
    public String update(@PathVariable Long id,@Validated(DTOOrdenServicio.OnUpdate.class) @RequestBody DTOOrdenServicio dto) {
        return ordenServicioService.update(id, dto);
    }

    // Actualizar parcialmente (PATCH)
    //RUTA localhost:8080/apiOrdenServicio/patch/id
    @PatchMapping("/patch/{id}")
    //RUTA localhost:8080/apiOrdenServicio/patch/id
    public String patch(@PathVariable Long id, @RequestBody DTOOrdenServicio dto) {
        return ordenServicioService.patch(id, dto);
    }

    // Eliminar por ID
    //localhost:8080/apiOrdenServicio/delete/id
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return ordenServicioService.delete(id);
    }
}