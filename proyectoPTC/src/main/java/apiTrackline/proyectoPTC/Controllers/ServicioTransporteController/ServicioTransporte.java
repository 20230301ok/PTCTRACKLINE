package apiTrackline.proyectoPTC.Controllers.ServicioTransporteController;

import apiTrackline.proyectoPTC.Models.DTO.DTOServicioTransporte;
import apiTrackline.proyectoPTC.Services.ServicioTransporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/apiServicioTransporte")
public class ServicioTransporte {
    @Autowired
    private ServicioTransporteService service;

    // Obtener todos los servicios de transporte
    // Ruta: GET localhost:8080/apiServicioTransporte/data
    @GetMapping("/data")
    public List<DTOServicioTransporte> getServicioTransporte() {
        return service.getServicioTrans();
    }

    // Agregar servicios de transporte
    // Ruta: POST localhost:8080/apiServicioTransporte/postST
    @PostMapping("/postST")
    public ResponseEntity<String> postServicioTransporte(@RequestBody @Valid DTOServicioTransporte dto, BindingResult result) {
        if (result.hasErrors()) {
            // Devuelve el primer mensaje de validación si hay errores
            return ResponseEntity.badRequest().body(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }

        // Llama al servicio para guardar los servicios de transporte
        String mensaje = service.postServicioTrans(dto);
        return ResponseEntity.ok(mensaje);
    }

    // Actualizar parcialmente (un campo) los servicios de transporte
    // Ruta: patch localhost:8080/apiServicioTransporte/patchST/id
    @PatchMapping("/patchST/{id}")
    public ResponseEntity<String> patchServicioTransporte(@PathVariable Long id, @RequestBody @Valid DTOServicioTransporte dto, BindingResult result) {
        if (result.hasErrors()) {
            // Devuelve el primer mensaje de validación si hay errores
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        // Llama al servicio para actualizar un campo del registro
        String mensaje = service.patchServicioTrans(id, dto);
        return ResponseEntity.ok(mensaje);
    }

    //Elimina un registro
    //Ruta: DELETE localhost:8080/apiServicioTransporte/deleteST/id
    @DeleteMapping("/deleteST/{id}")
    public ResponseEntity<String> deleteServicioTransporte(@PathVariable Long id){
        // Llama al servicio para eliminar un registro
        String mensaje = service.deleteServicioTrans(id);
        return ResponseEntity.ok(mensaje);
    }

    // Actualizar completamente un servicio de transporte
    // Ruta: PUT localhost:8080/apiServicioTransporte/updateTS/{id}
    @PutMapping("/updateTS/{id}")
    public ResponseEntity<String> updateServicioTransporte(@PathVariable Long id, @RequestBody @Valid DTOServicioTransporte dto, BindingResult result) {
        if (result.hasErrors()) {
            // Devuelve el primer mensaje de error de validación
            return ResponseEntity.badRequest()
                    .body(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        // Llama al servicio para hacer la actualización
        String mensaje = service.updateServicioTrans(id, dto);
        if (mensaje.toLowerCase().contains("no se encontró")) {
            return ResponseEntity.notFound().build();
        }
        // Éxito
        return ResponseEntity.ok(mensaje);
    }


}
