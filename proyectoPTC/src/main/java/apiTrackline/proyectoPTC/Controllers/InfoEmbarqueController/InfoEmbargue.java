package apiTrackline.proyectoPTC.Controllers.InfoEmbarqueController;

import apiTrackline.proyectoPTC.Models.DTO.DTOInfoEmbarque;
import apiTrackline.proyectoPTC.Services.InfoEmbarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiInfoEmbarque")
public class InfoEmbargue {

    @Autowired //Inyectamos la clase InfoEmbarqueService
    private InfoEmbarqueService service;

    //Se usa GetMapping porque es el metodo HTTP que usaremos
    //La ruta sería localhost:8080/apiInfoEmbarque/dataInfoEmb
    @GetMapping("/dataInfoEmb")
    public List<DTOInfoEmbarque> getUser(){
        return service.obtenerInfoEmb();
    }

    @PostMapping("/postInfoEmb")
    //La ruta sería localhost:8080/apiInfoEmbarque/postInfoEmb
    public String postInfoEmb(@RequestBody DTOInfoEmbarque dtoInfoEmbarque) {
        return service.post(dtoInfoEmbarque);
    }

    @DeleteMapping("/deleteInfoEmb/{id}")
    //La ruta sería localhost:8080/apiInfoEmbarque/deleteInfoEmb/{id}
    public String deleteInfoEmb(@PathVariable("id") long id) {
        return service.deleteInfoEmb(id);
    }

    @PutMapping("/updateInfoEmb/{id}")
    //La ruta sería localhost:8080/apiInfoEmbarque/updateInfoEmb/{id}
    public String updateInfoEmb(@PathVariable("id") long id, @RequestBody DTOInfoEmbarque dtoInfoEmbarque){
        return service.updateInfoEmb(id, dtoInfoEmbarque);
    }

    @PatchMapping("/updateInfoEmbPartial/{id}")
    //La ruta sería localhost:8080/apiInfoEmbarque/updateInfoEmbPartial/{id}
    public String patchInfoEmb(@PathVariable Long id, @RequestBody DTOInfoEmbarque dtoInfoEmbarque) {
        return service.patchInfEmb(id, dtoInfoEmbarque);
    }
}
