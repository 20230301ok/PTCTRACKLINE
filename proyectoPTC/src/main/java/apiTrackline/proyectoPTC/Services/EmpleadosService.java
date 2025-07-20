package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.ClientesEntity;
import apiTrackline.proyectoPTC.Entities.EmpleadosEntity;
import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOClientes;
import apiTrackline.proyectoPTC.Models.DTO.DTOEmpleados;
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
public class EmpleadosService {
    @Autowired
    private EmpleadosRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ClientesRepository clientesRepo;

    @Autowired
    private TransportistaRepository transportistasRepo;


    public List<DTOEmpleados> obtenerEmpleados(){
        List<EmpleadosEntity> entity = repo.findAll();
        return entity.stream()
                .map(this::convertirAEmpleadosDTO)
                .collect(Collectors.toList());
    }

    private DTOEmpleados convertirAEmpleadosDTO(EmpleadosEntity entity){
        DTOEmpleados dto = new DTOEmpleados();
        dto.setIdEmpleado(entity.getIdEmpleado());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setTelefono(entity.getTelefono());
        dto.setCorreo(entity.getCorreo());
        dto.setNit(entity.getNit());
        //Datos de la tabla usuario
        if(entity.getUsuarioEmpleado() != null){
            //Se crea un objeto de tipoUsuarioEntity y se le asigna el atributo que hace referencia a la llave foranea
            UsuarioEntity usuario = entity.getUsuarioEmpleado();
            dto.setIdUsuario(usuario.getIdUsuario());
            dto.setUsuario(usuario.getUsuario());
            dto.setContrasenia(usuario.getContrasenia());
            if (usuario.getRol() != null)
            {
                dto.setIdRol(usuario.getRol().getIdRol());
                dto.setNombreRol(usuario.getRol().getRol());
            }
        }
        return dto;
    }

    private boolean usuarioYaAsignado(Long idUsuario, Long idEmpleadoActual) {
        boolean usadoPorCliente = clientesRepo.existsByUsuario_IdUsuario(idUsuario); //Verifica que si está en uso el usuario que queremos ingresar en otra tabla (Clientes)
        boolean usadoPorTransportista = transportistasRepo.existsByUsuarioT_IdUsuario(idUsuario); //Verifica que si está en uso el usuario que queremos ingresar en otra tabla (Transportista)

        // Verificamos si está asignado a otro empleado diferente del actual
        boolean usadoPorOtroEmpleado = repo.existsByUsuarioEmpleado_IdUsuarioAndIdEmpleadoNot(idUsuario, idEmpleadoActual);

        return usadoPorCliente || usadoPorTransportista || usadoPorOtroEmpleado;
    }


    public String agregarEmpleado(DTOEmpleados dto){
        try {
            EmpleadosEntity entity = new EmpleadosEntity();
            entity.setNombre(dto.getNombre());
            entity.setApellido(dto.getApellido());
            entity.setTelefono(dto.getTelefono());
            entity.setCorreo(dto.getCorreo());
            entity.setNit(dto.getNit());

            if(dto.getIdUsuario() != null){
                //Verifica si el usuario que queremos ingresar ya está en uso
                //Se pasa "-1L" como ID actual para que no se excluya a sí mismo (porque aún no existe).
                if (usuarioYaAsignado(dto.getIdUsuario(), -1L)) {
                    //Si ya está asignado el usuario a un transportista o a un cliente mostrará mensaje de error
                    return "Error: El usuario ya está asignado a otro registro";
                }


                Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                //Verifica si existe el id usuario
                if(usuario.isPresent()){
                    entity.setUsuarioEmpleado(usuario.get());
                } else {
                    return "Error: usuario no encontrado";
                }
            }

            repo.save(entity);
            return "Se ha agregado el empleado correctamente";
        } catch (Exception e){
            return "Error al agregar el empleado: " + e.getMessage();
        }
    }


    public String actualizarEmpleado(Long id, DTOEmpleados dto){
        try {
            Optional<EmpleadosEntity> optional = repo.findById(id);

            if(optional.isPresent()){
                EmpleadosEntity entity = optional.get();
                entity.setNombre(dto.getNombre());
                entity.setApellido(dto.getApellido());
                entity.setTelefono(dto.getTelefono());
                entity.setCorreo(dto.getCorreo());
                entity.setNit(dto.getNit());

                if(dto.getIdUsuario() != null){
                    if (usuarioYaAsignado(dto.getIdUsuario(), id)) {
                        return "Error: El usuario ya está asignado a otro registro";
                    }

                    Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                    if(usuario.isPresent()){
                        entity.setUsuarioEmpleado(usuario.get());
                    } else {
                        return "Error: no se encontró un usuario con el id: " + dto.getIdUsuario();
                    }
                }

                repo.save(entity);
                return "Empleado actualizado correctamente";
            } else {
                return "Error: No se encontró el empleado con el id brindado";
            }
        } catch (Exception e){
            return "Error: no se pudo actualizar el empleado: " + e.getMessage();
        }
    }

    public String actualizarParcialmenteEmpleado(Long id, DTOEmpleados dto){
        Optional<EmpleadosEntity> optionalEmpleados = repo.findById(id);

        if (optionalEmpleados.isPresent()){
            EmpleadosEntity entity = optionalEmpleados.get();
            if(dto.getNombre() != null){
                entity.setNombre(dto.getNombre());
            }
            if(dto.getApellido() != null){
                entity.setApellido(dto.getApellido());
            }
            if (dto.getTelefono() != null){
                entity.setTelefono(dto.getTelefono());
            }
            if (dto.getCorreo() != null){
                entity.setCorreo(dto.getCorreo());
            }
            if(dto.getNit() != null){
                entity.setNit(dto.getNit());
            }
            if(dto.getIdUsuario() != null){
                if (usuarioYaAsignado(dto.getIdUsuario(), id)) {
                    return "Error: El usuario ya está asignado a otro registro";
                }

                Optional<UsuarioEntity> usuario = usuarioRepo.findById(dto.getIdUsuario());
                if(usuario.isPresent()){
                    entity.setUsuarioEmpleado(usuario.get());
                } else {
                    return "No se encontró el usuario con id: " + dto.getIdUsuario();
                }
            }
            repo.save(entity);
            return "Se ha actualizado parcialmente el empleado correctamente";
        } else {
            return "No se encontró el empleado con id: " + id;
        }
    }


    public String eliminarEmpleado(Long id){
        Optional<EmpleadosEntity> optional = repo.findById(id);
        if(optional.isPresent()){
            repo.deleteById(id);
            return "Empleado eliminado correctamente";
        }
        else {
            return "Empleado no encontrado con id: " + id;
        }
    }
}
