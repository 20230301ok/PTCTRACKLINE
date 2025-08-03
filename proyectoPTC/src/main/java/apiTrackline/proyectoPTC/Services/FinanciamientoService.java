package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOFinanciamiento;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinanciamientoService {

    @Autowired
    private FinanciamientoRepository repo;

    @Autowired
    private TipoFinanciamientosRepository tipoRepo;

    public List<DTOFinanciamiento> getData() {
        List<FinanciamientoEntity> lista = repo.findAll();
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private DTOFinanciamiento convertirADTO(FinanciamientoEntity f) {
        DTOFinanciamiento dto = new DTOFinanciamiento();
        dto.setIdFinanciamiento(f.getIdFinanciamiento());
        dto.setMonto(f.getMonto());
        if (f.getTipoFinanciamiento() != null) {
            dto.setIdTipoFinanciamiento(f.getTipoFinanciamiento().getIdTipoFinanciamiento());
            dto.setNombreTipoFinanciamiento(f.getTipoFinanciamiento().getNombre());
        }
        return dto;
    }

    public String post(DTOFinanciamiento dto) {
        try {
            FinanciamientoEntity f = new FinanciamientoEntity();
            Optional<TipoFinanciamientosEntity> tipo = tipoRepo.findById(dto.getIdTipoFinanciamiento());
            if (tipo.isEmpty()) return "Tipo de financiamiento no encontrado.";

            f.setTipoFinanciamiento(tipo.get());
            f.setMonto(dto.getMonto());
            repo.save(f);
            return "Financiamiento creado correctamente.";
        } catch (Exception e) {
            return "Error al crear financiamiento: " + e.getMessage();
        }
    }

    public String update(Long id, DTOFinanciamiento dto) {
        Optional<FinanciamientoEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            FinanciamientoEntity f = optional.get();
            f.setMonto(dto.getMonto());

            if (dto.getIdTipoFinanciamiento() != null) {
                Optional<TipoFinanciamientosEntity> tipo = tipoRepo.findById(dto.getIdTipoFinanciamiento());
                tipo.ifPresent(f::setTipoFinanciamiento);
            }

            repo.save(f);
            return "Financiamiento actualizado correctamente.";
        } else {
            return "Financiamiento no encontrado.";
        }
    }

    public String patch(Long id, DTOFinanciamiento dto) {
        Optional<FinanciamientoEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            FinanciamientoEntity f = optional.get();
            if (dto.getMonto() != null) f.setMonto(dto.getMonto());

            if (dto.getIdTipoFinanciamiento() != null) {
                Optional<TipoFinanciamientosEntity> tipo = tipoRepo.findById(dto.getIdTipoFinanciamiento());
                tipo.ifPresent(f::setTipoFinanciamiento);
            }

            repo.save(f);
            return "Financiamiento actualizado parcialmente.";
        }
        return "Financiamiento no encontrado.";
    }

    public String delete(Long id) {
        try{
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return "Financiamiento eliminado correctamente.";
            } else {
                return "Financiamiento no encontrado.";
            }
        }catch (Exception e){
            return "Error al eliminar el financiamiento: " + e.getMessage();
        }

    }
}
