package apiTrackline.proyectoPTC.Controllers.ClientesController;

import apiTrackline.proyectoPTC.Models.DTO.DTOClientes;
import apiTrackline.proyectoPTC.Services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiClientes")
@CrossOrigin
public class Clientes {

    @Autowired
    private ClientesService service;


    //Metodo GET
    //Ruta: localhost:8080/apiClientes/obtenerClientes
    @GetMapping("/obtenerClientes")
    public List<DTOClientes> obtenerClientes(){
        return service.obtenerClientes();
    }

    //Metodo Post
    //Ruta: localhost:8080/apiClientes/agregarCliente
    @PostMapping("/agregarCliente")
    public String agregarCliente(@Validated(DTOClientes.OnCreate.class) @RequestBody DTOClientes dto){
        return service.agregarCliente(dto);
    }



    // PUT
    //localhost:8080/apiClientes/actualizarcliente/clienteNit
    @PutMapping("/actualizarCliente/{clienteNit}")
    public String actualizarCliente(@PathVariable String clienteNit,
                                    @Validated(DTOClientes.OnUpdate.class) @RequestBody DTOClientes dto) {
        return service.actualizarCliente(clienteNit, dto);
    }

    // PATCH
    //localhost:8080/apiClientes/actualizarParcialmente/clienteNit
    @PatchMapping("/actualizarParcialmente/{clienteNit}")
    public String actualizarParcial(@PathVariable String clienteNit,
                                    @Validated(DTOClientes.OnPatch.class) @RequestBody DTOClientes dtoClientes) {
        return service.patchCliente(clienteNit, dtoClientes);
    }

    // DELETE
    //localhost:8080/apiClientes/eliminarCliente/clienteNit
    @DeleteMapping("/eliminarCliente/{clienteNit}")
    public String eliminar(@PathVariable String clienteNit) {
        return service.eliminarCliente(clienteNit);
    }

}
