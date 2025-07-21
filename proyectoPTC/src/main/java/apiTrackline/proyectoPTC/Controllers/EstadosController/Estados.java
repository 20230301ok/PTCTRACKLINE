package apiTrackline.proyectoPTC.Controllers.EstadosController;

import apiTrackline.proyectoPTC.Models.DTO.DTOEstados;
import apiTrackline.proyectoPTC.Services.EstadosService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiEstados")
public class Estados {

    @Autowired
    private EstadosService service;

    // GET: Obtener todos los estados
    // Ruta: GET /apiEstados/obtenerEstados
    @GetMapping("/obtenerEstados")
    public List<DTOEstados> obtenerDatos(){
        return service.obtenerEstados();
    }

    // POST: Crear un nuevo estado
    // Ruta: POST /apiEstados/agregar
    @PostMapping("/agregar")
    public String agregar(@RequestBody @Validated({DTOEstados.OnCreate.class, Default.class}) DTOEstados dto) {
        return service.agregarEstado(dto);
    }

    // PUT: Actualizar completamente un estado
    // Ruta: PUT /apiEstados/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @RequestBody @Validated({DTOEstados.OnUpdate.class}) DTOEstados dto) {
        return service.actualizarEstado(id, dto);
    }

    // PATCH: Actualizar parcialmente un estado
    // Ruta: PATCH /apiEstados/patch/{id}
    @PatchMapping("/patch/{id}")
    public String patch(@PathVariable Long id, @RequestBody @Validated({DTOEstados.OnPatch.class}) DTOEstados dto) {
        return service.patchEstado(id, dto);
    }

    // DELETE: Eliminar un estado por su ID
    // Ruta: DELETE /apiEstados/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        return service.eliminarEstado(id);
    }
}
