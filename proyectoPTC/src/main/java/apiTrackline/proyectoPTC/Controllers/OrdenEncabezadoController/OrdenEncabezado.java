package apiTrackline.proyectoPTC.Controllers.OrdenEncabezadoController;

import apiTrackline.proyectoPTC.Exceptions.OrdenEncabezadoExceptions.ExceptionOrdenEncabezadoNoEncontrado;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenEncabezado;
import apiTrackline.proyectoPTC.Services.OrdenEncabezadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiOrden")  // Ruta base: localhost:8080/apiOrden
public class OrdenEncabezado {
    @Autowired  // Inyecta el servicio que contiene la lógica de negocio
    private OrdenEncabezadoService service;

    //Obtener una órden encabezado por id
    //Ruta: GET localhost:8080/apiOrden/buscarPorId/id
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{
            DTOOrdenEncabezado ordenEncabezado = service.buscarPorId(id);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "data", ordenEncabezado
            ));
        } catch (ExceptionOrdenEncabezadoNoEncontrado e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error no controlado",
                    "message", "Ocurrió un error no controlado"
            ));
        }
    }

    // Obtener todas las órdenes
    // Ruta: GET localhost:8080/apiOrden/dataOrden
    @GetMapping("/dataOrden")
    public ResponseEntity<?> getOrdenes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        if (page < 0) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Error de validación",
                    "message", "El número de página no puede ser negativo"
            ));
        }

        if (size <= 0 || size > 50) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Error de validación",
                    "message", "El tamaño de la página debe estar entre 1 y 50"
            ));
        }

        Page<DTOOrdenEncabezado> ordenEncabezados = service.getData(page, size);
        if (ordenEncabezados == null || ordenEncabezados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", "No hay orden encabezados registrados"
            ));
        }

        return ResponseEntity.ok(ordenEncabezados);
    }


    // Crear una nueva orden
    // Ruta: POST localhost:8080/apiOrden/postOrden
    @PostMapping("/postOrden")
    public String postOrden(@Validated(DTOOrdenEncabezado.OnCreate.class) @RequestBody DTOOrdenEncabezado dto) {
        return service.post(dto);
    }

    // Actualizar completamente una orden existente
    // Ruta: PUT localhost:8080/apiOrden/updateOrden/id
    @PutMapping("/updateOrden/{id}")
    public String updateOrden(@PathVariable Long id, @Validated(DTOOrdenEncabezado.OnUpdate.class) @RequestBody DTOOrdenEncabezado dto) {
        return service.update(id, dto);
    }

    // Actualizar parcialmente una orden existente
    // Ruta: PATCH localhost:8080/apiOrden/updateOrdenPartial/id
    @PatchMapping("/updateOrdenPartial/{id}")
    public String patchOrden(@PathVariable Long id, @Validated(DTOOrdenEncabezado.OnPatch.class) @RequestBody DTOOrdenEncabezado dto) {
        return service.patchOrden(id, dto);
    }

    // Eliminar una orden
    // Ruta: DELETE localhost:8080/apiOrden/deleteOrden/id
    @DeleteMapping("/deleteOrden/{id}")
    public String deleteOrden(@PathVariable Long id) {
        return service.delete(id);
    }
}
