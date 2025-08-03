package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import apiTrackline.proyectoPTC.Entities.OrdenEncabezadoEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenEncabezado;
import apiTrackline.proyectoPTC.Repositories.OrdenEncabezadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import apiTrackline.proyectoPTC.Exceptions.OrdenEncabezadoExceptions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenEncabezadoService {
    @Autowired
    private OrdenEncabezadoRepository repo;

    // Obtiene todos los registros de la tabla y convertirlos en una lista de DTO
    public Page<DTOOrdenEncabezado> getData(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrdenEncabezadoEntity> pageEntity = repo.findAll(pageable);
        return pageEntity.map(this::convertirADTO);
        //TODO LO QUE SALE DE LA BASE SALE COMO ENTIDAD
        //TODO LO QUE ENTRA A LA BASE DEBE ENTRAR COMO ENTIDAD
    }
    //Convierte las entidades a dto
    private DTOOrdenEncabezado convertirADTO(OrdenEncabezadoEntity orden) {
        DTOOrdenEncabezado dto = new DTOOrdenEncabezado();
        dto.setIdOrdenEncabezado(orden.getIdOrdenEncabezado());
        dto.setFecha(orden.getFecha());
        dto.setEncargado(orden.getEncargado());
        dto.setReferencia(orden.getReferencia());
        dto.setImportador(orden.getImportador());
        dto.setNIT(orden.getNit());
        dto.setRegistroIVA(orden.getRegistroIva());
        dto.setFacturaA(orden.getFacturaA());
        dto.setEncargado2(orden.getEncargado2());
        dto.setNIT2(orden.getNit2());
        dto.setRegistroIVA2(orden.getRegistroIva2());
        return dto;
    }

    //POST
    public String post(DTOOrdenEncabezado dto) {
        try {
            OrdenEncabezadoEntity orden = new OrdenEncabezadoEntity();
            orden.setFecha(dto.getFecha());
            orden.setEncargado(dto.getEncargado());
            orden.setReferencia(dto.getReferencia());
            orden.setImportador(dto.getImportador());
            orden.setNit(dto.getNIT());
            orden.setRegistroIva(dto.getRegistroIVA());
            orden.setFacturaA(dto.getFacturaA());
            orden.setEncargado2(dto.getEncargado2());
            orden.setNit2(dto.getNIT2());
            orden.setRegistroIva2(dto.getRegistroIVA2());

            repo.save(orden);
            return "Orden creada correctamente";
        }
        catch (Exception e){
            return "Error al crear la orden: " + e.getMessage();
        }
    }

    //UPDATE
    //localhost:8080/apiOrdenEncabezado/dataOrdenEncabezado/1
    public String update(Long id, DTOOrdenEncabezado dto) {
        Optional<OrdenEncabezadoEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            OrdenEncabezadoEntity orden = optional.get();

            orden.setFecha(dto.getFecha());
            orden.setEncargado(dto.getEncargado());
            orden.setReferencia(dto.getReferencia());
            orden.setImportador(dto.getImportador());
            orden.setNit(dto.getNIT());
            orden.setRegistroIva(dto.getRegistroIVA());
            orden.setFacturaA(dto.getFacturaA());
            orden.setEncargado2(dto.getEncargado2());
            orden.setNit2(dto.getNIT2());
            orden.setRegistroIva2(dto.getRegistroIVA2());

            repo.save(orden);
            return "Orden actualizada correctamente";
        }
        return "Orden no encontrada con ID: " + id;
    }

    //PATCH
    public String patchOrden(Long id, DTOOrdenEncabezado dto) {
        Optional<OrdenEncabezadoEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            OrdenEncabezadoEntity orden = optional.get();

            // Solo actualiza los campos que vienen con valores diferentes a  null
            if (dto.getFecha() != null) orden.setFecha(dto.getFecha());
            if (dto.getEncargado() != null) orden.setEncargado(dto.getEncargado());
            if (dto.getReferencia() != null) orden.setReferencia(dto.getReferencia());
            if (dto.getImportador() != null) orden.setImportador(dto.getImportador());
            if (dto.getNIT() != null) orden.setNit(dto.getNIT());
            if (dto.getRegistroIVA() != null) orden.setRegistroIva(dto.getRegistroIVA());
            if (dto.getFacturaA() != null) orden.setFacturaA(dto.getFacturaA());
            if (dto.getEncargado2() != null) orden.setEncargado2(dto.getEncargado2());
            if (dto.getNIT2() != null) orden.setNit(dto.getNIT2());
            if (dto.getRegistroIVA2() != null) orden.setRegistroIva2(dto.getRegistroIVA2());

            repo.save(orden);
            return "Orden actualizada parcialmente.";
        }
        return "Orden no encontrada.";
    }

    //Eliminar
    public String delete(Long id) {
        Optional<OrdenEncabezadoEntity> optional = repo.findById(id); //Elimina por  ID identificado
        if (optional.isPresent()){
            repo.deleteById(id);
            return "Orden eliminada correctamente";
        } else {
            return "Orden no encontrada";
        }
    }

    public DTOOrdenEncabezado buscarPorId(Long id) {
        OrdenEncabezadoEntity ordenEncabezado = repo.findById(id).
                orElseThrow(() -> new ExceptionOrdenEncabezadoNoEncontrado("Orden encabezado no encontrado con id: " + id));
        return convertirADTO(ordenEncabezado);
    }
}