package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.RolesEntity;
import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOUsuario;
import apiTrackline.proyectoPTC.Repositories.RolesRepository;
import apiTrackline.proyectoPTC.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RolesRepository rolesRepo;

    // Método público que usa el repositorio y convierte entidades a DTOs
    //Método HTTP GET (obtener datos)
    public List<DTOUsuario> getData() {
        List<UsuarioEntity> users = repo.findAll();
        return users.stream()
                .map(this::convertirAUsuarioDTO)
                .collect(Collectors.toList());
    }

    //Convierte los datos del usuario a DTO
    private DTOUsuario convertirAUsuarioDTO(UsuarioEntity user){
        DTOUsuario dto = new DTOUsuario();
        dto.setIdUsuario(user.getIdUsuario());
        dto.setUsuario(user.getUsuario());
        dto.setContrasenia(user.getContrasenia());

        // Verificamos si el objeto relacionado "id rol" no es nulo (evitamos NullPointerException)
        if (user.getRol() != null) {
            // Se obtiene el ID del rol relacionado
            dto.setIdRol(user.getRol().getIdRol());
            // También se obtiene el nombre del rol
            dto.setRol(user.getRol().getRol());
        } else {
            // Si es nulo, se dejan en null ambos campos
            dto.setIdRol(null);
            dto.setRol(null);
        }
        return dto;
    }

    //Método HTTP PUT(actualizar) por id
    public String update(long id, DTOUsuario dtoUsuario){
        Optional<UsuarioEntity> optionalUser = repo.findById(id);
        if (optionalUser.isPresent()) {
            if (repo.existsByUsuarioAndIdUsuarioNot(dtoUsuario.getUsuario(), id)) {
                return "Error: El nombre de usuario ya está en uso por otro usuario";
            }
            UsuarioEntity user = optionalUser.get();
            user.setUsuario(dtoUsuario.getUsuario());
            user.setContrasenia(dtoUsuario.getContrasenia());
            // Si también se quiere actualizar el rol:
            if (user.getRol() != null) {
                Optional<RolesEntity> usuario = rolesRepo.findById(dtoUsuario.getIdRol());
                usuario.ifPresent(user::setRol);
            }
            repo.save(user);
            return "Usuario actualizado correctamente";
        } else {
            return "Usuario no encontrado con ID: " + (id);
        }
    }

    //Método HTTP DELETE(eliminar) por id
    public String delete(Long id) {
        Optional<UsuarioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Usuario eliminado correctamente";
        } else {
            return "Usuario no encontrado";
        }
    }

    //Método HTTP POST(Insertar)
    public String post(DTOUsuario dtoUser) {
        try {
            //Verificar que no haya un nombre de usuario igual al momento de crear el usuario
            if (repo.existsByUsuario(dtoUser.getUsuario())) {
                return "Error: El nombre de usuario ya existe.";
            }

            UsuarioEntity user = new UsuarioEntity();
            user.setUsuario(dtoUser.getUsuario());
            user.setContrasenia(dtoUser.getContrasenia());

            Optional<RolesEntity> roles = rolesRepo.findById(dtoUser.getIdRol());

            //Si el id del rol existe, se crea correctamente el usuario
            if (roles.isPresent()) {
                user.setRol(roles.get());
                repo.save(user);
                return "Usuario creado correctamente";
            }
            else { //Si no se encuentra el id de rol, se muestra el error
                return "Error: ID de rol no encontrado";
            }

        } catch (Exception e) {
            return "Error al crear el usuario: " + e.getMessage();
        }
    }


    //Método HTTP PATCH(actualizar un solo campo) por id
    public String patchUser(Long id, DTOUsuario dto) {
        Optional<UsuarioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            UsuarioEntity user = optional.get();
            if (repo.existsByUsuarioAndIdUsuarioNot(dto.getUsuario(), id)) {
                return "Error: El nombre de usuario ya está en uso por otro usuario";
            }
            if (dto.getUsuario() != null) {
                user.setUsuario(dto.getUsuario());
            }
            if (dto.getContrasenia() != null) {
                user.setContrasenia(dto.getContrasenia());
            }
            if (dto.getIdRol() != null) {
                Optional<RolesEntity> usuario = rolesRepo.findById(dto.getIdRol());
                usuario.ifPresent(user::setRol);
            }

            repo.save(user);
            return "Usuario actualizado parcialmente";
        }
        return "Usuario no encontrado";
    }
}


