package apiTrackline.proyectoPTC.Controllers.TransportistaController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTransportista;
import apiTrackline.proyectoPTC.Services.TransportistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTransportista")
public class Transportista {
    @Autowired
    private TransportistaService service;

    // Obtener todos los transportistas
    //Ruta: GET localhost:8080/apiTransportista/dataTransportista
    @GetMapping("/dataTransportista")
    public List<DTOTransportista> getTransportistas() {
        return service.getData();
    }

    //Crear un nuevo transportista
    //Ruta: POST localhost:8080/apiTransportista/postTransportista
    @PostMapping("/postTransportista")
    public String postTransportista(@Valid @RequestBody DTOTransportista dto) {
        return service.post(dto);
    }

    //Actualiza completamente a un transportista
    //Ruta: PUT localhost:8080/apiTransportista/updateTransportista/{id}
    @PutMapping("/updateTransportista/{id}")
    public String updateTransportista(@PathVariable Long id, @Valid @RequestBody DTOTransportista dto) {
        return service.update(id, dto);
    }

    //Actualiza parcialmente a un transportista
    //Ruta: PATCH localhost:8080/apiTransportista/updateTransportistaPartial/{id}
    @PatchMapping("/updateTransportistaPartial/{id}")
    public String patchTransportista(@PathVariable Long id,@Valid @RequestBody DTOTransportista dto) {
        return service.patch(id, dto);
    }

    //Elimina transportista
    //Ruta: DELETE localhost:8080/apiTransportista/deleteTransportista/{id}
    @DeleteMapping("/deleteTransportista/{id}")
    public String deleteTransportista(@PathVariable Long id) {
        return service.delete(id);
    }
}