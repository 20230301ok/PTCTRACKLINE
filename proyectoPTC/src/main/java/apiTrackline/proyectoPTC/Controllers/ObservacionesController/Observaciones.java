package apiTrackline.proyectoPTC.Controllers.ObservacionesController;
import apiTrackline.proyectoPTC.Models.DTO.DTOObservaciones;
import apiTrackline.proyectoPTC.Services.ObservacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiObservaciones")
public class Observaciones {

    @Autowired
    private ObservacionesService service;

    @GetMapping("/data")
    public List<DTOObservaciones> getAll() {
        return service.getData();
    }

    @PostMapping("/create")
    public String create(@Validated(DTOObservaciones.OnCreate.class) @RequestBody DTOObservaciones dto) {
        return service.post(dto);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @Validated(DTOObservaciones.OnUpdate.class) @RequestBody DTOObservaciones dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/patch/{id}")
    public String patch(@PathVariable Long id, @Validated(DTOObservaciones.OnPatch.class) @RequestBody DTOObservaciones dto) {
        return service.patch(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
