package apiTrackline.proyectoPTC.Controllers.TrackingController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTracking;
import apiTrackline.proyectoPTC.Services.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiTracking")
public class Tracking {

    @Autowired
    private TrackingService service;

    @GetMapping("/list")
    public List<DTOTracking> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DTOTracking getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public DTOTracking post(@RequestBody DTOTracking dto) {
        return service.post(dto);
    }

    @PutMapping("/{id}")
    public DTOTracking update(@RequestBody DTOTracking dto, @PathVariable Long id) {
        return service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
