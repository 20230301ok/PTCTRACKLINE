package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.EstadosEntity;
import apiTrackline.proyectoPTC.Entities.OrdenServicioEntity;
import apiTrackline.proyectoPTC.Entities.SelectivoEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOEstados;
import apiTrackline.proyectoPTC.Repositories.EstadosRepository;
import apiTrackline.proyectoPTC.Repositories.OrdenServicioRepository;
import apiTrackline.proyectoPTC.Repositories.SelectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EstadosService {

    @Autowired
    private EstadosRepository repo;

    @Autowired
    private OrdenServicioRepository ordenServicioRepo;

    @Autowired
    private SelectivoRepository selectivoRepo;

    public List<DTOEstados> obtenerEstados() {
        List<EstadosEntity> estados = repo.findAll();
        return estados.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOEstados convertirADTO(EstadosEntity entity) {
        DTOEstados dto = new DTOEstados();
        dto.setIdEstado(entity.getIdEstado());
        dto.setDocumentos(entity.getDocumentos());
        dto.setClasificacion(entity.getClasificacion());
        dto.setDigitacion(entity.getDigitacion());
        dto.setRegistro(entity.getRegistro());
        dto.setPago(entity.getPago());
        dto.setLevantePago(entity.getLevantePago());
        dto.setEquipoTransporte(entity.getEquipoTransporte());
        dto.setCarga(entity.getCarga());
        dto.setEnCamino(entity.getEnCamino());
        dto.setEntregada(entity.getEntregada());
        dto.setFacturacion(entity.getFacturacion());

        if (entity.getOrdenServicioEstados() != null)
            dto.setIdOrdenServicio(entity.getOrdenServicioEstados().getIdOrdenServicio());

        if (entity.getSelectivo() != null) {
            dto.setIdSelectivo(entity.getSelectivo().getIdSelectivo());
            dto.setColorSelectivo(entity.getSelectivo().getColorSelectivo());
        }

        return dto;
    }

    public String agregarEstado(DTOEstados dto) {
        try {
            // Validaciones de booleanos
            if (!validarBooleanos(dto)) {
                return "Error: Los campos booleanos deben ser true o false.";
            }

            EstadosEntity entity = new EstadosEntity();
            entity.setDocumentos(dto.getDocumentos());
            entity.setClasificacion(dto.getClasificacion());
            entity.setDigitacion(dto.getDigitacion());
            entity.setRegistro(dto.getRegistro());
            entity.setPago(dto.getPago());
            entity.setLevantePago(dto.getLevantePago());
            entity.setEquipoTransporte(dto.getEquipoTransporte());
            entity.setCarga(dto.getCarga());
            entity.setEnCamino(dto.getEnCamino());
            entity.setEntregada(dto.getEntregada());
            entity.setFacturacion(dto.getFacturacion());

            // Validar existencia de orden de servicio
            if (dto.getIdOrdenServicio() != null) {
                Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
                if (orden.isEmpty()) return "Error: No se encontró Orden de Servicio con ID " + dto.getIdOrdenServicio();
                entity.setOrdenServicioEstados(orden.get());
            }

            // Validar existencia de selectivo
            if (dto.getIdSelectivo() != null) {
                Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
                if (selectivo.isEmpty()) return "Error: No se encontró Selectivo con ID " + dto.getIdSelectivo();
                entity.setSelectivo(selectivo.get());
            }

            repo.save(entity);
            return "Estado agregado correctamente";
        } catch (Exception e) {
            return "Error al agregar estado: " + e.getMessage();
        }
    }


    public String actualizarEstado(Long id, DTOEstados dto) {
        Optional<EstadosEntity> optional = repo.findById(id);
        if (optional.isEmpty()) return "Estado no encontrado con ID: " + id;

        if (!validarBooleanos(dto)) {
            return "Error: Los campos booleanos deben ser true o false.";
        }

        EstadosEntity entity = optional.get();
        entity.setDocumentos(dto.getDocumentos());
        entity.setClasificacion(dto.getClasificacion());
        entity.setDigitacion(dto.getDigitacion());
        entity.setRegistro(dto.getRegistro());
        entity.setPago(dto.getPago());
        entity.setLevantePago(dto.getLevantePago());
        entity.setEquipoTransporte(dto.getEquipoTransporte());
        entity.setCarga(dto.getCarga());
        entity.setEnCamino(dto.getEnCamino());
        entity.setEntregada(dto.getEntregada());
        entity.setFacturacion(dto.getFacturacion());

        if (dto.getIdOrdenServicio() != null) {
            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            if (orden.isEmpty()) {
                return "Error: No se encontró Orden de Servicio con ID " + dto.getIdOrdenServicio();
            }
            entity.setOrdenServicioEstados(orden.get());
        }

        if (dto.getIdSelectivo() != null) {
            Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
            if (selectivo.isEmpty()){
                return "Error: No se encontró Selectivo con ID " + dto.getIdSelectivo();
            }
            entity.setSelectivo(selectivo.get());
        }

        repo.save(entity);
        return "Estado actualizado correctamente";
    }


    public String patchEstado(Long id, DTOEstados dto) {
        Optional<EstadosEntity> optional = repo.findById(id);
        if (optional.isEmpty()) return "Estado no encontrado con ID: " + id;

        if (!validarBooleanos(dto)) {
            return "Error: Los campos booleanos deben ser true o false.";
        }

        EstadosEntity entity = optional.get();

        if (dto.getDocumentos() != null) entity.setDocumentos(dto.getDocumentos());
        if (dto.getClasificacion() != null) entity.setClasificacion(dto.getClasificacion());
        if (dto.getDigitacion() != null) entity.setDigitacion(dto.getDigitacion());
        if (dto.getRegistro() != null) entity.setRegistro(dto.getRegistro());
        if (dto.getPago() != null) entity.setPago(dto.getPago());
        if (dto.getLevantePago() != null) entity.setLevantePago(dto.getLevantePago());
        if (dto.getEquipoTransporte() != null) entity.setEquipoTransporte(dto.getEquipoTransporte());
        if (dto.getCarga() != null) entity.setCarga(dto.getCarga());
        if (dto.getEnCamino() != null) entity.setEnCamino(dto.getEnCamino());
        if (dto.getEntregada() != null) entity.setEntregada(dto.getEntregada());
        if (dto.getFacturacion() != null) entity.setFacturacion(dto.getFacturacion());

        if (dto.getIdOrdenServicio() != null) {
            Optional<OrdenServicioEntity> orden = ordenServicioRepo.findById(dto.getIdOrdenServicio());
            if (orden.isEmpty()) return "Error: No se encontró Orden de Servicio con ID " + dto.getIdOrdenServicio();
            entity.setOrdenServicioEstados(orden.get());
        }

        if (dto.getIdSelectivo() != null) {
            Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
            if (selectivo.isEmpty()) return "Error: No se encontró Selectivo con ID " + dto.getIdSelectivo();
            entity.setSelectivo(selectivo.get());
        }

        repo.save(entity);
        return "Estado actualizado parcialmente";
    }


    public String eliminarEstado(Long id) {
        Optional<EstadosEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Estado eliminado correctamente";
        } else {
            return "Estado no encontrado con ID: " + id;
        }
    }

    private boolean validarBooleanos(DTOEstados dto) {
        try {
            // Si alguno no es null, entonces debe ser true o false (ya lo garantiza el tipo Boolean)
            // Aquí simplemente validamos que no haya valores inesperados (evitando null si lo necesitas)
            return Stream.of(
                    dto.getDocumentos(),
                    dto.getClasificacion(),
                    dto.getDigitacion(),
                    dto.getRegistro(),
                    dto.getPago(),
                    dto.getLevantePago(),
                    dto.getEquipoTransporte(),
                    dto.getCarga(),
                    dto.getEnCamino(),
                    dto.getEntregada(),
                    dto.getFacturacion()
            ).allMatch(b -> b == null || b instanceof Boolean);
        } catch (Exception e) {
            return false;
        }
    }

}
