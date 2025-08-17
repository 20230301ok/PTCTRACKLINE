package apiTrackline.proyectoPTC.Controllers.OrdenServicioController;

import apiTrackline.proyectoPTC.Exceptions.AduanaExceptions.ExceptionAduanaNoEncontrada;
import apiTrackline.proyectoPTC.Exceptions.CargosExceptions.ExceptionCargosNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.ClientesExceptions.ExceptionClienteNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.EstadosExceptions.ExceptionOrdenServicioNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.FinanciamientoExceptions.ExceptionFinanciamientoNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.InfoEmbarqueExceptions.ExceptionInfoEmbarqueNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.ObservacionesExceptions.ExceptionObservacionNoEncontrada;
import apiTrackline.proyectoPTC.Exceptions.OrdenEncabezadoExceptions.ExceptionOrdenEncabezadoNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.OrdenServicioExceptions.ExceptionOrdenServicioNoRegistrado;
import apiTrackline.proyectoPTC.Exceptions.OrdenServicioExceptions.ExceptionOrdenServicioRelacionada;
import apiTrackline.proyectoPTC.Exceptions.RecoleccionExceptions.ExceptionRecoleccionNoEncontrado;
import apiTrackline.proyectoPTC.Exceptions.TransporteExceptions.ExceptionTransporteNoEncontrado;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Services.OrdenServicioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiOrdenServicio")
@CrossOrigin
@Slf4j
public class OrdenServicio {

    @Autowired
    private OrdenServicioService service;

    // MÉTODO GET POR ID
    // RUTA: localhost:8080/apiOrdenServicio/buscarPorId/{id}
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarOrdenServicioPorId(@PathVariable Long id) {
        try {
            DTOOrdenServicio orden = service.buscarOrdenServicioPorId(id);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "data", orden
            ));
        } catch (ExceptionOrdenServicioNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            log.error("Error inesperado al obtener orden servicio por id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "message", "Error inesperado al buscar orden de servicio por ID"
            ));
        }
    }

    // MÉTODO GET - Obtener todos
    // RUTA: localhost:8080/apiOrdenServicio/datosOrdenesServicio
    @GetMapping("/datosOrdenesServicio")
    public ResponseEntity<?> obtenerOrdenes(
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

        Page<DTOAduana> aduanas = service.obtenerAduanas(page, size);
        if (aduanas == null || aduanas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", "No hay aduanas registradas"
            ));
        }
        return ResponseEntity.ok(aduanas);
    }

    // MÉTODO POST
    // RUTA: localhost:8080/apiOrdenServicio/crear
    @PostMapping("/crear")
    public ResponseEntity<?> crearOrdenServicio(@Validated(DTOOrdenServicio.OnCreate.class) @RequestBody DTOOrdenServicio dto) {
        try {
            DTOOrdenServicio guardado = service.post(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Éxito",
                    "data", guardado,
                    "message", "Orden de servicio creada correctamente"
            ));
        } catch (ExceptionClienteNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (ExceptionOrdenServicioNoRegistrado e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            log.error("Error inesperado al crear orden de servicio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "message", "Error no controlado al registrar orden de servicio"
            ));
        }
    }

    // MÉTODO PUT
    // RUTA: localhost:8080/apiOrdenServicio/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarOrdenServicio(@PathVariable Long id,
                                                     @Validated(DTOOrdenServicio.OnUpdate.class) @RequestBody DTOOrdenServicio dto) {
        try {
            DTOOrdenServicio actualizada = service.update(id, dto);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "data", actualizada,
                    "message", "Orden de servicio actualizada correctamente"
            ));
        } catch (ExceptionOrdenServicioNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (ExceptionClienteNoEncontrado | ExceptionOrdenEncabezadoNoEncontrado |
                 ExceptionInfoEmbarqueNoEncontrado | ExceptionAduanaNoEncontrada |
                 ExceptionTransporteNoEncontrado | ExceptionRecoleccionNoEncontrado |
                 ExceptionCargosNoEncontrado | ExceptionFinanciamientoNoEncontrado |
                 ExceptionObservacionNoEncontrada llaveForaneaNoEncontrada) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "Error",
                    "descripcion", "Error llave foránea no encontrada",
                    "message", llaveForaneaNoEncontrada.getMessage()
            ));
        } catch (Exception e) {
            log.error("Error inesperado al actualizar orden de servicio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "message", "Error no controlado al actualizar orden de servicio"
            ));
        }
    }


    // MÉTODO PATCH
    // RUTA: localhost:8080/apiOrdenServicio/actualizarParcial/{id}
    @PatchMapping("/actualizarParcial/{id}")
    public ResponseEntity<?> patchOrdenServicio(@PathVariable Long id,
                                                @Validated(DTOOrdenServicio.OnPatch.class) @RequestBody DTOOrdenServicio dto) {
        try {
            String resultado = service.patch(id, dto);
            if (resultado.startsWith("Error")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                        "status", "Error",
                        "message", resultado
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "message", resultado
            ));
        } catch (Exception e) {
            log.error("Error inesperado al hacer patch en orden de servicio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "message", "Error no controlado al actualizar parcialmente la orden de servicio"
            ));
        }
    }

    // MÉTODO DELETE
    // RUTA: localhost:8080/apiOrdenServicio/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOrdenServicio(@PathVariable Long id) {
        try {
            String mensaje = service.eliminarOrdenServicio(id);
            return ResponseEntity.ok(Map.of(
                    "status", "Éxito",
                    "message", mensaje
            ));
        } catch (ExceptionOrdenServicioNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status:", "Error",
                    "message", e.getMessage()
            ));
        } catch (ExceptionOrdenServicioRelacionada e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "Error",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            log.error("Error inesperado al eliminar orden de servicio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error interno",
                    "message", "Error no controlado al eliminar orden de servicio"
            ));
        }
    }
}