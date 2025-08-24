package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.ClientesEntity;
import apiTrackline.proyectoPTC.Entities.EmpleadosEntity;
import apiTrackline.proyectoPTC.Entities.UsuarioEntity;
import apiTrackline.proyectoPTC.Exceptions.EmpleadosExceptions.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOClientes;
import apiTrackline.proyectoPTC.Models.DTO.DTOEmpleados;
import apiTrackline.proyectoPTC.Repositories.ClientesRepository;
import apiTrackline.proyectoPTC.Repositories.EmpleadosRepository;
import apiTrackline.proyectoPTC.Repositories.TransportistaRepository;
import apiTrackline.proyectoPTC.Repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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


    public Page<DTOEmpleados> obtenerEmpleados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmpleadosEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::convertirAEmpleadosDTO);
        //TODO LO QUE SALE DE LA BASE SALE COMO ENTIDAD
        //TODO LO QUE ENTRA A LA BASE DEBE ENTRAR COMO ENTIDAD
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


    public DTOEmpleados agregarEmpleado(DTOEmpleados dto) {
        if (dto == null) {
            throw new IllegalArgumentException("No puedes agregar un registro sin datos");
        }

        try {
            EmpleadosEntity entity = new EmpleadosEntity();
            entity.setNombre(dto.getNombre());
            entity.setApellido(dto.getApellido());
            entity.setTelefono(dto.getTelefono());
            entity.setCorreo(dto.getCorreo());
            entity.setNit(dto.getNit());

            // Si el DTO trae usuario asignado, verificar si está libre
            if (dto.getIdUsuario() != null) {
                // Se pasa -1L para indicar que es nuevo y evitar excluirlo
                if (usuarioYaAsignado(dto.getIdUsuario(), -1L)) {
                    throw new ExceptionEmpleadoUsuarioYaAsignado("El usuario con ID " + dto.getIdUsuario() + " ya está asignado a otro registro");
                }

                // Buscar usuario
                UsuarioEntity usuario = usuarioRepo.findById(dto.getIdUsuario())
                        .orElseThrow(() -> new ExceptionEmpleadoUsuarioNoEncontrado("Usuario no encontrado con ID " + dto.getIdUsuario()));

                entity.setUsuarioEmpleado(usuario);
            }

            EmpleadosEntity empleadoCreado = repo.save(entity);
            return convertirAEmpleadosDTO(empleadoCreado);

        }
        catch (Exception e) {
            log.error("Error al agregar el empleado: ", e);
            throw new ExceptionEmpleadoNoRegistrado("Error: empleado no registrado");
        }
    }



    public DTOEmpleados actualizarEmpleado(Long id, DTOEmpleados dto) {
        EmpleadosEntity empleado = repo.findById(id)
                .orElseThrow(() -> new ExceptionEmpleadoNoEncontrado("Empleado no encontrado con id " + id));

        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setTelefono(dto.getTelefono());
        empleado.setCorreo(dto.getCorreo());
        empleado.setNit(dto.getNit());

        if (dto.getIdUsuario() != null) {
            if (usuarioYaAsignado(dto.getIdUsuario(), id)) {
                throw new ExceptionEmpleadoUsuarioYaAsignado("El usuario con ID " + dto.getIdUsuario() + " ya está asignado a otro registro");
            }

            UsuarioEntity usuario = usuarioRepo.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ExceptionEmpleadoUsuarioNoEncontrado("Usuario no encontrado con ID " + dto.getIdUsuario()));

            empleado.setUsuarioEmpleado(usuario);
        }

        return convertirAEmpleadosDTO(repo.save(empleado));
    }


    public DTOEmpleados patchEmpleado(Long id, DTOEmpleados dto) {
        EmpleadosEntity empleado = repo.findById(id)
                .orElseThrow(() -> new ExceptionEmpleadoNoEncontrado("Empleado no encontrado con id " + id));

        if (dto.getNombre() != null) {
            if (dto.getNombre().isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar en blanco");
            }
            empleado.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            empleado.setApellido(dto.getApellido());
        }
        if (dto.getTelefono() != null) empleado.setTelefono(dto.getTelefono());
        if (dto.getCorreo() != null) empleado.setCorreo(dto.getCorreo());
        if (dto.getNit() != null) empleado.setNit(dto.getNit());

        if (dto.getIdUsuario() != null) {
            if (usuarioYaAsignado(dto.getIdUsuario(), id)) {
                throw new ExceptionEmpleadoUsuarioYaAsignado("El usuario con ID " + dto.getIdUsuario() + " ya está asignado a otro registro");
            }

            UsuarioEntity usuario = usuarioRepo.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ExceptionEmpleadoUsuarioNoEncontrado("Usuario no encontrado con ID " + dto.getIdUsuario()));

            empleado.setUsuarioEmpleado(usuario);
        }

        return convertirAEmpleadosDTO(repo.save(empleado));
    }



    public String eliminarEmpleado(Long id){
       EmpleadosEntity empleado = repo.findById(id)
               .orElseThrow(() -> new ExceptionEmpleadoNoEncontrado("Empleado no encontrado con id: " + id));
        try {
            repo.delete(empleado);
            return "Empleado eliminado correctamente";
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionEmpleadoRelacionado("No se pudo eliminar el empleado porque tiene registros relacionados");
        }
    }

    public DTOEmpleados buscarEmpleadoPorId(Long id) {
        EmpleadosEntity entity = repo.findById(id)
                .orElseThrow(() -> new ExceptionEmpleadoNoEncontrado("No se encontró el empleado con ID: " + id));
        return convertirAEmpleadosDTO(entity);
    }
}
