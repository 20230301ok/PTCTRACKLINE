package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOUsuario;
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
        dto.setIdRol(user.getIdRol());
        return dto;
    }

    //Método HTTP PUT(actualizar) por id
    public String update(long id, DTOUsuario userDto){
        Optional<UsuarioEntity> optionalUser = repo.findById(id);
        if (optionalUser.isPresent()) {
            UsuarioEntity user = optionalUser.get();
            user.setUsuario(userDto.getUsuario());
            user.setContrasenia(userDto.getContrasenia());
            user.setIdRol(userDto.getIdRol());
            repo.save(user);
            return "Usuario actualizado correctamente";
        } else {
            return "Usuario no encontrado con ID: " + (id);
        }
    }

    //Método HTTP DELETE(eliminar) por id
    public String delete(long id) {
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
            UsuarioEntity user = new UsuarioEntity();
            user.setUsuario(dtoUser.getUsuario());
            user.setContrasenia(dtoUser.getContrasenia());
            user.setIdRol(dtoUser.getIdRol());

            repo.save(user);
            return "Usuario creado correctamente";
        } catch (Exception e) {
            return "Error al crear el usuario: " + e.getMessage();
        }
    }

    //Método HTTP PATCH(actualizar un solo campo) por id
    public String patchUser(Long id, DTOUsuario dto) {
        Optional<UsuarioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            UsuarioEntity user = optional.get();

            if (dto.getUsuario() != null) {
                user.setUsuario(dto.getUsuario());
            }
            if (dto.getContrasenia() != null) {
                user.setContrasenia(dto.getContrasenia());
            }
            if (dto.getIdRol() != 0) {
                user.setIdRol(dto.getIdRol());
            }

            repo.save(user);
            return "Usuario actualizado parcialmente";
        }
        return "Usuario no encontrado";
    }
}


