package apiTrackline.proyectoPTC.Controllers.PermisosController;

import apiTrackline.proyectoPTC.Entities.PermisosEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOPermisos;
import apiTrackline.proyectoPTC.Services.PermisosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiPermisos")
public class Permisos {

    @Autowired
    private PermisosService service;

    //Método GET
    //RUTA: localhost:8080/apiPermisos/obtenerPermisos
    @GetMapping("/obtenerPermisos")
    public List<DTOPermisos> obtenerDatos(){
        return service.obtenerDatos();
    }
}
