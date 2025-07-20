package apiTrackline.proyectoPTC.Controllers.CargosController;

import apiTrackline.proyectoPTC.Models.DTO.DTOCargos;
import apiTrackline.proyectoPTC.Services.CargosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiCargos")
public class Cargos {

    @Autowired
    private CargosService service;

    @GetMapping("/data")
    public List<DTOCargos> getAll() {
        return service.getData();
    }

    @PostMapping("/create")
    public String create(@Validated(DTOCargos.OnCreate.class) @RequestBody DTOCargos dto) {
        return service.post(dto);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @Validated(DTOCargos.OnUpdate.class) @RequestBody DTOCargos dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/patch/{id}")
    public String patch(@PathVariable Long id, @Validated(DTOCargos.OnPatch.class) @RequestBody DTOCargos dto) {
        return service.patch(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
