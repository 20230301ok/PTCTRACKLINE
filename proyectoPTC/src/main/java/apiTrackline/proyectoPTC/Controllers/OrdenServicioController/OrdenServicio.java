package apiTrackline.proyectoPTC.Controllers.OrdenServicioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Services.OrdenServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiOrdenServicio")
public class OrdenServicio {

    @Autowired
    private OrdenServicioService service;

    @GetMapping("/dataOrdenServicio")
    public List<DTOOrdenServicio> getOrdenes() {
        return service.getData();
    }

    @PostMapping("/postOrdenServicio")
    public String create(@RequestBody DTOOrdenServicio dto) {
        return service.post(dto);
    }

    @PutMapping("/updateOrdenServicio/{id}")
    public String update(@PathVariable Long id, @RequestBody DTOOrdenServicio dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/deleteOrdenServicio/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}