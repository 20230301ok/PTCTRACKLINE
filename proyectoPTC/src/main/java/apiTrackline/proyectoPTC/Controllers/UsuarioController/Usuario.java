package apiTrackline.proyectoPTC.Controllers.UsuarioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOUsuario;
import apiTrackline.proyectoPTC.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiUsuario")
public class Usuario {
    @Autowired //Inyectamos la clase UserService
    private UsuarioService service;

    //Se usa GetMapping porque es el metodo HTTP que usaremos
    //La ruta sería localhost:8080/apiUsuario/dataUsuario
    @GetMapping("/dataUsuario")
    public List<DTOUsuario> getUser(){
        return service.getData();
    }

    // Método para actualizar un usuario
    //La ruta sería localhost:8080/apiUsuario/updateUsuario
    @PutMapping("/updateUsuario/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody DTOUsuario userDto){
        return service.update(id, userDto);
    }

    @DeleteMapping("/deleteUsuario/{id}")
    //La ruta sería localhost:8080/apiUsuario/deleteUsuario
    public String deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/postUsuario")
    //La ruta sería localhost:8080/apiUsuario/postUsuario
    public String postUser(@RequestBody DTOUsuario userDto) {
        return service.post(userDto);
    }

    @PatchMapping("/patchUsuario/{id}")
    //La ruta sería localhost:8080/apiUsuario/patchUsuario
    public String patchUser(@PathVariable Long id, @RequestBody DTOUsuario dto) {
        return service.patchUser(id, dto);
    }
}
