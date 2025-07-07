package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.OrdenEncabezadoEntity;
import apiTrackline.proyectoPTC.Entities.TransporteEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenEncabezado;
import apiTrackline.proyectoPTC.Models.DTO.DTOTransporte;
import apiTrackline.proyectoPTC.Repositories.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apiTrackline.proyectoPTC.Entities.TransportistaEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository repo;

    public List<DTOTransporte> obtenerTransporte() {
        List<TransporteRepository> transporte = repo.findAll();
        return transporte.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    //Convierte las entidades a dto
    private DTOTransporte convertirADTO(TransporteEntity transporte, ServicioTransporteIdentity serviciotrans, TransportistaEntity tranportista) {
        DTOTransporte dto = new DTOTransporte();
        dto.setIdTransporte(transporte.getIdTransporte());
        dto.setIdServicioTransporte(transporte.getIdServicioTransporte());
        dto.setIdTransportista(transporte.getIdTransportista());
        return dto;
    }

    //POST
    public String post(DTOTransporte dto) {
        try {
            TransporteEntity transporte = new TransporteEntity();
            transporte.setIdTransporte(dto.getIdTransporte());
            transporte.setIdServicioTransporte(dto.getIdServicioTransporte());



            repo.save(transporte);
            return "Transporte guardado correctamente";
        } catch (Exception e) {
            return "Error al guardar el transporte: " + e.getMessage();
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
}
