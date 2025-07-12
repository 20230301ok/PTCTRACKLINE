package apiTrackline.proyectoPTC.Controllers.SelectivoController;

import apiTrackline.proyectoPTC.Models.DTO.DTOSelectivo;
import apiTrackline.proyectoPTC.Services.SelectivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiSelectivo")
public class Selectivo{
    @Autowired
    private SelectivoService service;

    //Metodo GET
    //Ruta: localhost:8080/apiSelectivo/obtenerSelectivos
    @GetMapping("/obtenerSelectivos")
    public List<DTOSelectivo> obtenerSelectivos(){
        return service.obtenerSelectivos();
    }
}
