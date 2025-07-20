package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.TipoDatoContableEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOTipoDatoContable;
import apiTrackline.proyectoPTC.Repositories.TipoDatoContableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoDatoContableService {

    @Autowired
    private TipoDatoContableRepository repo;

    public List<DTOTipoDatoContable> getData() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DTOTipoDatoContable toDTO(TipoDatoContableEntity entity) {
        DTOTipoDatoContable dto = new DTOTipoDatoContable();
        dto.setIdTipoDatoContable(entity.getIdTipoDatoContable());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public String post(DTOTipoDatoContable dto) {
        try {
            TipoDatoContableEntity entity = new TipoDatoContableEntity();
            entity.setNombre(dto.getNombre());
            repo.save(entity);
            return "TipoDatoContable creado correctamente.";
        } catch (Exception e) {
            return "Error al crear TipoDatoContable: " + e.getMessage();
        }
    }

    public String update(Long id, DTOTipoDatoContable dto) {
        Optional<TipoDatoContableEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            TipoDatoContableEntity entity = optional.get();
            entity.setNombre(dto.getNombre());
            repo.save(entity);
            return "TipoDatoContable actualizado correctamente.";
        } else {
            return "TipoDatoContable no encontrado.";
        }
    }

    public String patch(Long id, DTOTipoDatoContable dto) {
        Optional<TipoDatoContableEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            TipoDatoContableEntity entity = optional.get();
            if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
            repo.save(entity);
            return "TipoDatoContable actualizado parcialmente.";
        } else {
            return "TipoDatoContable no encontrado.";
        }
    }

    public String delete(Long id) {
        Optional<TipoDatoContableEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "TipoDatoContable eliminado correctamente.";
        } else {
            return "TipoDatoContable no encontrado.";
        }
    }
}

