package apiTrackline.proyectoPTC.Controllers.OrdenEncabezadoController;

import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenEncabezado;
import apiTrackline.proyectoPTC.Services.OrdenEncabezadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiOrden")  // Ruta base: localhost:8080/apiOrden
public class OrdenEncabezado {
    @Autowired  // Inyecta el servicio que contiene la lógica de negocio
    private OrdenEncabezadoService service;

    // Obtener todas las órdenes
    // Ruta: GET localhost:8080/apiOrden/dataOrden
    @GetMapping("/dataOrden")
    public List<DTOOrdenEncabezado> getOrdenes() {
        return service.getData();
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
