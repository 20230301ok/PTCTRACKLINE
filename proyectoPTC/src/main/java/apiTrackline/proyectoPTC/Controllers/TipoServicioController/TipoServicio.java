package apiTrackline.proyectoPTC.Controllers.TipoServicioController;

import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Services.TipoServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTipoServicio")
public class TipoServicio {
    @Autowired //Inyecta la clase service que tiene la lógica de la empresa
    private TipoServicioService service;

    // Obtener todos los tipos de servicios
    // Ruta: GET localhost:8080/apiTipoServicio/data
    @GetMapping("/data")
    public List<DTOTipoServicio> getTipoServicio() {
        return service.obtenerTipoServicio();
    }

    // Agregar un tipos de servicios
    // Ruta: POST localhost:8080/apiTipoServicio/postTS
    @PostMapping("/postTS")
    public ResponseEntity<String> postTipoServicio(@RequestBody @Valid DTOTipoServicio dto, BindingResult result) {
        if (result.hasErrors()) {
            // Devuelve el primer mensaje de validación si hay errores
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        // Llama al servicio para guardar el tipo de servicio
        String mensaje = service.agregarTipoServicio(dto);
        return ResponseEntity.ok(mensaje);
    }

    // Actualizar parcialmente (un campo) el tipo de servicio
    // Ruta: patch localhost:8080/apiTipoServicio/patchTS/id
    @PatchMapping("/patchTS/{id}")
    public ResponseEntity<String> patchTipoServicio(@PathVariable Long id, @RequestBody @Valid DTOTipoServicio dto, BindingResult result) {
        if (result.hasErrors()) {
            // Devuelve el primer mensaje de validación si hay errores
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        // Llama al servicio para actualizar un campo del registro
        String mensaje = service.actualizarTipoServicio(id, dto);
        return ResponseEntity.ok(mensaje);
    }

    //Elimina un registro
    //Ruta: DELETE localhost:8080/apiTipoServicio/deleteTS/id
    @DeleteMapping("/deleteTS/{id}")
    public ResponseEntity<String> deleteTipoServicio(@PathVariable Long id){
        // Llama al servicio para eliminar un registro
        String mensaje = service.eliminarTipoServicio(id);
        return ResponseEntity.ok(mensaje);
    }


}
