package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.*;
import apiTrackline.proyectoPTC.Models.DTO.DTOTracking;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository repository;

    @Autowired
    private ViajeRepository viajeRepository;

//    @Autowired
//    private EstadoRepository estadoRepository;

    public List<DTOTracking> getAll() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DTOTracking getById(Long id) {
        Optional<TrackingEntity> entity = repository.findById(id);
        return entity.map(this::convertToDTO).orElse(null);
    }

    public DTOTracking post(DTOTracking dto) {
        TrackingEntity entity = convertToEntity(dto);
        return convertToDTO(repository.save(entity));
    }

    public DTOTracking update(DTOTracking dto, Long id) {
        Optional<TrackingEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            TrackingEntity entity = convertToEntity(dto);
            entity.setIdTracking(id);
            return convertToDTO(repository.save(entity));
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private DTOTracking convertToDTO(TrackingEntity entity) {
        DTOTracking dto = new DTOTracking();
        dto.setIdTracking(entity.getIdTracking());
        dto.setIdViaje(entity.getIdViaje().getIdViaje());
        //dto.setIdEstado(entity.getIdEstado().getIdEstado());
        dto.setHoraEstimadaPartida(entity.getHoraEstimadaPartida());
        dto.setHoraEstimadaLlegada(entity.getHoraEstimadaLlegada());
        dto.setHoraLlegada(entity.getHoraLlegada());
        dto.setHoraSalida(entity.getHoraSalida());
        dto.setLugarPartida(entity.getLugarPartida());
        dto.setLugarLlegada(entity.getLugarLlegada());
        return dto;
    }

    private TrackingEntity convertToEntity(DTOTracking dto) {
        TrackingEntity entity = new TrackingEntity();
        entity.setIdViaje(viajeRepository.findById(dto.getIdViaje()).orElse(null));
        //entity.setIdEstado(estadoRepository.findById(dto.getIdEstado()).orElse(null));
        entity.setHoraEstimadaPartida(dto.getHoraEstimadaPartida());
        entity.setHoraEstimadaLlegada(dto.getHoraEstimadaLlegada());
        entity.setHoraLlegada(dto.getHoraLlegada());
        entity.setHoraSalida(dto.getHoraSalida());
        entity.setLugarPartida(dto.getLugarPartida());
        entity.setLugarLlegada(dto.getLugarLlegada());
        return entity;
    }
}
