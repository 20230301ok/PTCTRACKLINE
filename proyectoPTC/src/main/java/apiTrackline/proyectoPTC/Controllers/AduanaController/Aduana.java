package apiTrackline.proyectoPTC.Controllers.AduanaController;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOAduana;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoServicio;
import apiTrackline.proyectoPTC.Services.AduanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiAduana")
public class Aduana {
    @Autowired
    private AduanaService service;

    //MÉTODO GET
    //RUTA: localhost:8080/apiAduana/datosAduana
    @GetMapping("/datosAduana")
    public List<DTOAduana> getAduana() {
        return service.obtenerAduana();
    }

    //MÉTODO POST
    //RUTA: localhost8080/apiAduana/datosAduana

}
