package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.ObservacionesEntity;
import apiTrackline.proyectoPTC.Entities.SelectivoEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOObservaciones;
import apiTrackline.proyectoPTC.Repositories.ObservacionesRepository;
import apiTrackline.proyectoPTC.Repositories.SelectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObservacionesService {

    @Autowired
    private ObservacionesRepository repo;

    @Autowired
    private SelectivoRepository selectivoRepo;

    public List<DTOObservaciones> getData() {
        return repo.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DTOObservaciones convertirADTO(ObservacionesEntity entity) {
        DTOObservaciones dto = new DTOObservaciones();
        dto.setIdObservaciones(entity.getIdObservaciones());
        dto.setObservaciones(entity.getObservaciones());

        if (entity.getIdSelectivo() != null) {
            dto.setIdSelectivo(entity.getIdSelectivo().getIdSelectivo());
            dto.setColorSelectivo(entity.getIdSelectivo().getColorSelectivo());
        }

        return dto;
    }

    public String post(DTOObservaciones dto) {
        try {
            ObservacionesEntity entity = new ObservacionesEntity();
            entity.setObservaciones(dto.getObservaciones());

            Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
            if (selectivo.isPresent()) {
                entity.setIdSelectivo(selectivo.get());
                repo.save(entity);
                return "Observación creada correctamente.";
            } else {
                return "Error: ID de selectivo no encontrado.";
            }
        } catch (Exception e) {
            return "Error al crear la observación: " + e.getMessage();
        }
    }

    public String update(Long id, DTOObservaciones dto) {
        Optional<ObservacionesEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            ObservacionesEntity entity = optional.get();
            entity.setObservaciones(dto.getObservaciones());

            if (dto.getIdSelectivo() != null) {
                Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
                selectivo.ifPresent(entity::setIdSelectivo);
            }

            repo.save(entity);
            return "Observación actualizada correctamente.";
        } else {
            return "Observación no encontrada.";
        }
    }

    public String patch(Long id, DTOObservaciones dto) {
        Optional<ObservacionesEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            ObservacionesEntity entity = optional.get();

            if (dto.getObservaciones() != null) entity.setObservaciones(dto.getObservaciones());
            if (dto.getIdSelectivo() != null) {
                Optional<SelectivoEntity> selectivo = selectivoRepo.findById(dto.getIdSelectivo());
                selectivo.ifPresent(entity::setIdSelectivo);
            }

            repo.save(entity);
            return "Observación actualizada parcialmente.";
        } else {
            return "Observación no encontrada.";
        }
    }

    public String delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Observación eliminada correctamente.";
        } else {
            return "Observación no encontrada.";
        }
    }
}