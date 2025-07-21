package apiTrackline.proyectoPTC.Controllers.FinanciamientoController;


import apiTrackline.proyectoPTC.Models.DTO.DTOFinanciamiento;
import apiTrackline.proyectoPTC.Services.FinanciamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiFinanciamiento")
public class Financiamiento {

    @Autowired
    private FinanciamientoService service;

    //RUTA localhost:8080/apiFinanciamiento/data
    @GetMapping("/data")
    public List<DTOFinanciamiento> getAll() {
        return service.getData();
    }

    //localhost:8080/apiFinanciamiento/create
    @PostMapping("/create")
    public String create(@Validated(DTOFinanciamiento.OnCreate.class) @RequestBody DTOFinanciamiento dto) {
        return service.post(dto);
    }

    //localhost:8080/apiFinanciamiento/update/id
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @Validated(DTOFinanciamiento.OnUpdate.class) @RequestBody DTOFinanciamiento dto) {
        return service.update(id, dto);
    }

    //localhost:8080/apiFinanciamiento/patch/id
    @PatchMapping("/patch/{id}")
    public String patch(@PathVariable Long id, @Validated(DTOFinanciamiento.OnPatch.class) @RequestBody DTOFinanciamiento dto) {
        return service.patch(id, dto);
    }

    //localhost:8080/apiFinanciamiento/delete/id
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

