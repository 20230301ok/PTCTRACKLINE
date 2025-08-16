package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.EstadosEntity;
import apiTrackline.proyectoPTC.Exceptions.EstadosExceptions.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOEstados;
import apiTrackline.proyectoPTC.Repositories.EstadosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EstadosService {

    @Autowired
    private EstadosRepository repo;

    // ================== MÉTODOS ==================

    // GET PAGINADO
    public Page<DTOEstados> obtenerEstados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EstadosEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::convertirADTO);
    }

    // Convertir Entity → DTO
    private DTOEstados convertirADTO(EstadosEntity entity) {
        DTOEstados dto = new DTOEstados();
        dto.setIdEstado(entity.getIdEstado());
        dto.setDocumentos(entity.getDocumentos());
        dto.setClasificacion(entity.getClasificacion());
        dto.setDigitacion(entity.getDigitacion());
        dto.setRegistro(entity.getRegistro());
        dto.setPago(entity.getPago());

        if (entity.getSelectivo() != null) {
            dto.setIdSelectivo(entity.getSelectivo().getIdSelectivo());
            dto.setColorSelectivo(entity.getSelectivo().getColorSelectivo());
        }

        dto.setLevantePago(entity.getLevantePago());
        dto.setEquipoTransporte(entity.getEquipoTransporte());
        dto.setCarga(entity.getCarga());
        dto.setEnCamino(entity.getEnCamino());
        dto.setEntregada(entity.getEntregada());
        dto.setFacturacion(entity.getFacturacion());

        return dto;
    }

    // CREATE
    public DTOEstados agregarEstado(DTOEstados dto) {
        if (dto == null) throw new IllegalArgumentException("No puedes agregar un estado vacío");
        try {
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

            EstadosEntity creado = repo.save(entity);
            return convertirADTO(creado);

        } catch (Exception e) {
            log.error("Error al registrar el estado: " + e);
            throw new ExceptionEstadoNoRegistrado("Error: estado no registrado");
        }
    }

    // UPDATE
    public DTOEstados actualizarEstado(Long id, DTOEstados dto) {
        EstadosEntity estado = repo.findById(id)
                .orElseThrow(() -> new ExceptionEstadoNoEncontrado("Estado no encontrado con id " + id));

        estado.setDocumentos(dto.getDocumentos());
        estado.setClasificacion(dto.getClasificacion());
        estado.setDigitacion(dto.getDigitacion());
        estado.setRegistro(dto.getRegistro());
        estado.setPago(dto.getPago());
        estado.setLevantePago(dto.getLevantePago());
        estado.setEquipoTransporte(dto.getEquipoTransporte());
        estado.setCarga(dto.getCarga());
        estado.setEnCamino(dto.getEnCamino());
        estado.setEntregada(dto.getEntregada());
        estado.setFacturacion(dto.getFacturacion());

        return convertirADTO(repo.save(estado));
    }

    // PATCH
    public DTOEstados patchEstado(Long id, DTOEstados dto) {
        EstadosEntity estado = repo.findById(id)
                .orElseThrow(() -> new ExceptionEstadoNoEncontrado("Estado no encontrado con id " + id));

        if (dto.getDocumentos() != null) estado.setDocumentos(dto.getDocumentos());
        if (dto.getClasificacion() != null) estado.setClasificacion(dto.getClasificacion());
        if (dto.getDigitacion() != null) estado.setDigitacion(dto.getDigitacion());
        if (dto.getRegistro() != null) estado.setRegistro(dto.getRegistro());
        if (dto.getPago() != null) estado.setPago(dto.getPago());
        if (dto.getLevantePago() != null) estado.setLevantePago(dto.getLevantePago());
        if (dto.getEquipoTransporte() != null) estado.setEquipoTransporte(dto.getEquipoTransporte());
        if (dto.getCarga() != null) estado.setCarga(dto.getCarga());
        if (dto.getEnCamino() != null) estado.setEnCamino(dto.getEnCamino());
        if (dto.getEntregada() != null) estado.setEntregada(dto.getEntregada());
        if (dto.getFacturacion() != null) estado.setFacturacion(dto.getFacturacion());

        return convertirADTO(repo.save(estado));
    }

    // DELETE
    public String eliminarEstado(Long id) {
        EstadosEntity estado = repo.findById(id)
                .orElseThrow(() -> new ExceptionEstadoNoEncontrado("Estado no encontrado con id " + id));
        try {
            repo.delete(estado);
            return "Estado eliminado correctamente";
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionEstadoRelacionado("No se pudo eliminar el estado porque tiene registros relacionados");
        }
    }

    // GET BY ID
    public DTOEstados buscarEstadoPorId(Long id) {
        EstadosEntity entity = repo.findById(id)
                .orElseThrow(() -> new ExceptionEstadoNoEncontrado("No se encontró estado con ID: " + id));
        return convertirADTO(entity);
    }
}
