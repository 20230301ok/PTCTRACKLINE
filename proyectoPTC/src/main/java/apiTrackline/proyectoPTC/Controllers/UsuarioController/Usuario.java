package apiTrackline.proyectoPTC.Controllers.UsuarioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOUsuario;
import apiTrackline.proyectoPTC.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiUser")
public class Usuario {
    @Autowired //Inyectamos la clase UserService
    private UsuarioService service;

    //Se usa GetMapping porque es el metodo HTTP que usaremos
    //La ruta sería localhost:8080/apiUser/dataUser
    @GetMapping("/dataUser")
    public List<DTOUsuario> getUser(){
        return service.getData();
    }

    // Método para actualizar un usuario
    //La ruta sería localhost:8080/apiUser/updateUser
    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody DTOUsuario userDto){
        return service.update(id, userDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    //La ruta sería localhost:8080/apiUser/deleteUser
    public String deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/postUser")
    //La ruta sería localhost:8080/apiUser/postUser
    public String postUser(@RequestBody DTOUsuario userDto) {
        return service.post(userDto);
    }

    @PatchMapping("/updateUserPartial/{id}")
    //La ruta sería localhost:8080/apiUser/updateUserPartial
    public String patchUser(@PathVariable Long id, @RequestBody DTOUsuario dto) {
        return service.patchUser(id, dto);
    }
}
