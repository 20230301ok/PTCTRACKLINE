package apiTrackline.proyectoPTC.Controllers.TipoDatoContableController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTipoDatoContable;
import apiTrackline.proyectoPTC.Services.TipoDatoContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTipoDatoContable")
@CrossOrigin
public class TipoDatoContable {

    @Autowired
    private TipoDatoContableService service;

    @GetMapping("/data")
    public List<DTOTipoDatoContable> getAll() {
        return service.getData();
    }

    @PostMapping("/post")
    public String create(@Validated(DTOTipoDatoContable.OnCreate.class) @RequestBody DTOTipoDatoContable dto) {
        return service.post(dto);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @Validated(DTOTipoDatoContable.OnUpdate.class) @RequestBody DTOTipoDatoContable dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/patch/{id}")
    public String patch(@PathVariable Long id,
                        @Validated(DTOTipoDatoContable.OnPatch.class) @RequestBody DTOTipoDatoContable dto) {
        return service.patch(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}