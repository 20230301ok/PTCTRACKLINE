package apiTrackline.proyectoPTC.Controllers.EmpleadosController;

import apiTrackline.proyectoPTC.Models.DTO.DTOClientes;
import apiTrackline.proyectoPTC.Models.DTO.DTOEmpleados;
import apiTrackline.proyectoPTC.Services.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiEmpleados")
public class EmpleadosController {

    @Autowired
    private EmpleadosService service;

    //Metodo GET
    //RUTA: localhost:8080/apiEmpleados/obtenerEmpleados
    @GetMapping("/obtenerEmpleados")
    public List<DTOEmpleados> obtenerEmpleados(){
        return service.obtenerEmpleados();
    }

    //Metodo POST
    //RUTA: localhost:8080/apiEmpleados/agregarEmpleado
    @PostMapping("/agregarEmpleado")
    public String agregarEmpleado(@Validated(DTOEmpleados.OnCreate.class) @RequestBody DTOEmpleados dtoEmpleados){
        return service.agregarEmpleado(dtoEmpleados);
    }

    //Metodo PUT
    //RUTA: localhost:8080/apiEmpleados/actualizarEmpleado/id
    @PutMapping("/actualizarEmpleado/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @Validated(DTOEmpleados.OnUpdate.class) @RequestBody DTOEmpleados dtoEmpleados){
        return service.actualizarEmpleado(id, dtoEmpleados);
    }

    //Metodo PATCH
    //RUTA: localhost:8080/apiEmpleados/actualizarParcialmente/id
    @PatchMapping("/actualizarParcialmente/{id}")
    public String actualizarParcialmenteEmpleado(@PathVariable Long id, @Validated(DTOEmpleados.OnPatch.class) @RequestBody DTOEmpleados dtoEmpleados){
        return service.actualizarParcialmenteEmpleado(id, dtoEmpleados);
    }

    //Metodo DELETE
    //RUTA: localhost:8080/apiEmpleados/eliminarEmpleado/id
    @DeleteMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        return service.eliminarEmpleado(id);
    }
}
