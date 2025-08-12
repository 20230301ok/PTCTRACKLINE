package apiTrackline.proyectoPTC.Controllers.UsuarioController;

import apiTrackline.proyectoPTC.Models.DTO.DTOUsuario;
import apiTrackline.proyectoPTC.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiUsuario")
@CrossOrigin
public class Usuario {

    @Autowired
    private UsuarioService service;

    @GetMapping("/dataUsuario")
    public List<DTOUsuario> getUser() {
        return service.getData();
    }

    @PutMapping("/updateUsuario/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody @Validated(DTOUsuario.OnUpdate.class) DTOUsuario userDto) {
        return service.update(id, userDto);
    }

    @DeleteMapping("/deleteUsuario/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }

    @PostMapping("/postUsuario")
    public String postUser(@RequestBody @Validated(DTOUsuario.OnCreate.class) DTOUsuario userDto) {
        return service.post(userDto);
    }

    @PatchMapping("/patchUsuario/{id}")
    public String patchUser(@PathVariable Long id, @RequestBody @Validated(DTOUsuario.OnPatch.class) DTOUsuario dto) {
        return service.patchUser(id, dto);
    }
}
