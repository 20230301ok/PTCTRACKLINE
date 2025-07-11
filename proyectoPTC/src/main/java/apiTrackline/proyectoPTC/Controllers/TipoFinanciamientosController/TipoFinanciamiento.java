package apiTrackline.proyectoPTC.Controllers.TipoFinanciamientosController;

import apiTrackline.proyectoPTC.Models.DTO.DTOTipoFinanciamientos;
import apiTrackline.proyectoPTC.Services.TipoFinanciamientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiTipoF")
public class TipoFinanciamiento {

    @Autowired
    public TipoFinanciamientosService service;

    @GetMapping("/obtenerTF")
    //RUTA GET: localhost8080:apiTipoF/obtenerTF
    public List<DTOTipoFinanciamientos> obtenerDatos(){
        return service.obtenerTipoFinanciamiento();
    }
}
