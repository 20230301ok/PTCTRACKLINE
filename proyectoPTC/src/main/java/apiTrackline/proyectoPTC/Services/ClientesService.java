package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.ClientesEntity;
import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOClientes;
import apiTrackline.proyectoPTC.Repositories.ClientesRepository;
import apiTrackline.proyectoPTC.Repositories.EmpleadosRepository;
import apiTrackline.proyectoPTC.Repositories.TransportistaRepository;
import apiTrackline.proyectoPTC.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesService {
    @Autowired private ClientesRepository repo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private EmpleadosRepository empleadosRepo;
    @Autowired private TransportistaRepository transportistasRepo;

    public List<DTOClientes> obtenerClientes(){
        List<ClientesEntity> entity = repo.findAll();
        return entity.stream().map(this::convertirDTO).collect(Collectors.toList());
    }

    private DTOClientes convertirDTO(ClientesEntity entity){
        DTOClientes dto = new DTOClientes();
        dto.setClienteNit(entity.getClienteNit());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setCorreo(entity.getCorreo());
        dto.setTelefono(entity.getTelefono());
        dto.setCodEmpresa(entity.getCodEmpresa());

        if(entity.getUsuario() != null){
            dto.setIdUsuario(entity.getUsuario().getIdUsuario());
            dto.setUsuario(entity.getUsuario().getUsuario());
            dto.setContrasenia(entity.getUsuario().getContrasenia());

            if(entity.getUsuario().getRol() != null){
                dto.setIdRol(entity.getUsuario().getRol().getIdRol()); // ✅ nuevo
                dto.setNombreRol(entity.getUsuario().getRol().getRol());     // ✅ nuevo
            }
        }
        return dto;
    }

    private boolean usuarioYaAsignado(Long idUsuario) {
        return empleadosRepo.existsByUsuarioEmpleado_IdUsuario(idUsuario) ||
                transportistasRepo.existsByUsuarioT_IdUsuario(idUsuario) ||
                repo.existsByUsuario_IdUsuario(idUsuario);
    }

    public String agregarCliente(DTOClientes dto){
        try {
            ClientesEntity entity = new ClientesEntity();
            entity.setClienteNit(dto.getClienteNit());
            entity.setNombre(dto.getNombre());
            entity.setApellido(dto.getApellido());
            entity.setCorreo(dto.getCorreo());
            entity.setTelefono(dto.getTelefono());
            entity.setCodEmpresa(dto.getCodEmpresa());

            if(dto.getIdUsuario() != null){
                //Verifica si el usuario que queremos ingresar ya está en uso
                if(usuarioYaAsignado(dto.getIdUsuario())){
                    return "Error: Usuario ya está asignado a otro registro";
                }
                Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                if(usuario.isPresent()){
                    entity.setUsuario(usuario.get());
                } else {
                    return "Error: Usuario no encontrado";
                }
            }

            repo.save(entity);
            return "Cliente agregado correctamente";
        } catch (Exception e){
            return "Error al agregar cliente: " + e.getMessage();
        }
    }

    public String actualizarCliente(String nit, DTOClientes dto){
        Optional<ClientesEntity> optional = repo.findById(nit);
        if(optional.isPresent()){
            ClientesEntity entity = optional.get();
            entity.setNombre(dto.getNombre());
            entity.setApellido(dto.getApellido());
            entity.setCorreo(dto.getCorreo());
            entity.setTelefono(dto.getTelefono());

            if(dto.getIdUsuario() != null){
                // Se obtiene el ID del usuario actualmente asignado al cliente (si existe); si no tiene usuario asignado, se deja como null
                Long idActualUsuario = entity.getUsuario() != null ? entity.getUsuario().getIdUsuario() : null;
                if(!dto.getIdUsuario().equals(idActualUsuario) && usuarioYaAsignado(dto.getIdUsuario())){
                    return "Error: Usuario ya está asignado a otro registro";
                }

                Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                if(usuario.isPresent()){
                    entity.setUsuario(usuario.get());
                } else {
                    return "Usuario no encontrado";
                }
            }

            repo.save(entity);
            return "Cliente actualizado correctamente";
        } else {
            return "Cliente no encontrado con NIT: " + nit;
        }
    }


    public String patchCliente(String nit, DTOClientes dto){
        Optional<ClientesEntity> optional = repo.findById(nit);

        if(optional.isPresent()){
            ClientesEntity entity = optional.get();

            if(dto.getNombre() != null){
                entity.setNombre(dto.getNombre());
            }

            if(dto.getCorreo() != null){
                entity.setCorreo(dto.getCorreo());
            }

            if(dto.getTelefono() != null){
                entity.setTelefono(dto.getTelefono());
            }

            if(dto.getIdUsuario() != null){

                // Se obtiene el ID del usuario actualmente asignado al cliente (si existe); si no tiene usuario asignado, se deja como null
                Long idActualUsuario = entity.getUsuario() != null ? entity.getUsuario().getIdUsuario() : null;
                // Verifica que el usuario no esté asignado a otra entidad
                if(!dto.getIdUsuario().equals(idActualUsuario) && usuarioYaAsignado(dto.getIdUsuario())){
                    return "Error: Usuario ya está asignado a otro registro";
                }

                Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                if(usuario.isPresent()){
                    entity.setUsuario(usuario.get());
                } else {
                    return "Usuario no encontrado con id: " + dto.getIdUsuario();
                }
            }

            repo.save(entity);
            return "Cliente actualizado parcialmente";
        } else {
            return "Cliente no encontrado con NIT: " + nit;
        }
    }


    public String eliminarCliente(String nit){
        Optional<ClientesEntity> optional = repo.findById(nit);
        if(optional.isPresent()){
            repo.deleteById(nit);
            return "Cliente eliminado";
        }
        return "Cliente no encontrado con NIT: " + nit;
    }
}
