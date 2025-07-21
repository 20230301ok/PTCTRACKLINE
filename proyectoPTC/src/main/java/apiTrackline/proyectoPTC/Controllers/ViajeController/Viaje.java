package apiTrackline.proyectoPTC.Controllers.ViajeController;

import apiTrackline.proyectoPTC.Models.DTO.DTOViaje;
import apiTrackline.proyectoPTC.Services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiViaje")
public class Viaje {

    @Autowired
    private ViajeService service;

    //METODO GET
    //RUTA: localhost:8080/apiViaje/dataViajes
    @GetMapping("/dataViajes")
    public List<DTOViaje> getViajes() {
        return service.getData();
    }

    //METODO POST
    //RUTA: localhost:8080/apiViaje/postViaje
    @PostMapping("/postViaje")
    public String createViaje(@Validated(DTOViaje.OnCreate.class) @RequestBody DTOViaje dto) {
        return service.post(dto);
    }

    //METODO PUT
    //RUTA: localhost:8080/apiViaje/updateViaje/id
    @PutMapping("/updateViaje/{id}")
    public String updateViaje(@PathVariable Long id, @Validated(DTOViaje.OnUpdate.class) @RequestBody DTOViaje dto) {
        return service.update(id, dto);
    }

    //METODO PATCH
    //RUTA: localhost:8080/apiViaje/updateViajePartial/id
    @PatchMapping("/updateViajePartial/{id}")
    public String patchViaje(@PathVariable Long id, @Validated(DTOViaje.OnPatch.class) @RequestBody DTOViaje dto) {
        return service.patch(id, dto);
    }

    //METODO DELETE
    //RUTA: localhost:8080/apiViaje/deleteViaje/id
    @DeleteMapping("/deleteViaje/{id}")
    public String deleteViaje(@PathVariable Long id) {
        return service.delete(id);
    }
}
