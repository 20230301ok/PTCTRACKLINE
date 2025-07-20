package apiTrackline.proyectoPTC.Services;


import apiTrackline.proyectoPTC.Entities.OrdenServicioEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTOOrdenServicio;
import apiTrackline.proyectoPTC.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServicioService {

    @Autowired
    private OrdenServicioRepository repo;

    @Autowired
    private OrdenEncabezadoRepository ordenEncabezadoRepo;
    @Autowired
    private InfoEmbarqueRepository infoEmbarqueRepo;
    @Autowired
    private AduanaRepository aduanaRepo;
    @Autowired
    private TransporteRepository transporteRepo;
    @Autowired
    private RecoleccionRepository recoleccionRepo;
    @Autowired
    private CargosRepository cargosRepo;
    @Autowired
    private FinanciamientoRepository financiamientoRepo;
    @Autowired
    private ObservacionesRepository observacionesRepo;

    // Obtener todos los registros y convertirlos a DTO
    public List<DTOOrdenServicio> getData() {
        List<OrdenServicioEntity> lista = repo.findAll();
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    // Convertir Entity a DTO
    private DTOOrdenServicio convertirADTO(OrdenServicioEntity entity) {
        DTOOrdenServicio dto = new DTOOrdenServicio();
        dto.setIdOrdenServicio(entity.getIdOrdenServicio());
        dto.setClienteNIT(entity.getClienteNIT());

        if (entity.getIdOrdenEncabezado() != null)
            dto.setIdOrdenEncabezado(entity.getIdOrdenEncabezado().getIdOrdenEncabezado());

        if (entity.getIdInfoEmbarque() != null)
            dto.setIdInfoEmbarque(entity.getIdInfoEmbarque().getIdInfoEmbarque());

        if (entity.getIdAduana() != null)
            dto.setIdAduana(entity.getIdAduana().getIdAduana());

        if (entity.getIdTransporte() != null)
            dto.setIdTransporte(entity.getIdTransporte().getIdTransporte());

        if (entity.getIdRecoleccion() != null)
            dto.setIdRecoleccion(entity.getIdRecoleccion().getIdRecoleccion());

        if (entity.getIdCargos() != null)
            dto.setIdCargos(entity.getIdCargos().getIdCargos());

        if (entity.getIdFinanciamiento() != null)
            dto.setIdFinanciamiento(entity.getIdFinanciamiento().getIdFinanciamiento());

        if (entity.getIdObservaciones() != null)
            dto.setIdObservaciones(entity.getIdObservaciones().getIdObservaciones());

        return dto;
    }

    // POST - Crear
    public String post(DTOOrdenServicio dto) {
        try {
            OrdenServicioEntity entity = new OrdenServicioEntity();
            entity.setClienteNIT(dto.getClienteNIT());

            ordenEncabezadoRepo.findById(dto.getIdOrdenEncabezado()).ifPresent(entity::setIdOrdenEncabezado);
            infoEmbarqueRepo.findById(dto.getIdInfoEmbarque()).ifPresent(entity::setIdInfoEmbarque);
            aduanaRepo.findById(dto.getIdAduana()).ifPresent(entity::setIdAduana);
            transporteRepo.findById(dto.getIdTransporte()).ifPresent(entity::setIdTransporte);
            recoleccionRepo.findById(dto.getIdRecoleccion()).ifPresent(entity::setIdRecoleccion);
            cargosRepo.findById(dto.getIdCargos()).ifPresent(entity::setIdCargos);
            financiamientoRepo.findById(dto.getIdFinanciamiento()).ifPresent(entity::setIdFinanciamiento);
            observacionesRepo.findById(dto.getIdObservaciones()).ifPresent(entity::setIdObservaciones);

            repo.save(entity);
            return "Orden de servicio creada correctamente";
        } catch (Exception e) {
            return "Error al crear orden de servicio: " + e.getMessage();
        }
    }

    // PUT - Actualizar totalmente
    public String update(Long id, DTOOrdenServicio dto) {
        Optional<OrdenServicioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            OrdenServicioEntity entity = optional.get();
            entity.setClienteNIT(dto.getClienteNIT());

            ordenEncabezadoRepo.findById(dto.getIdOrdenEncabezado()).ifPresent(entity::setIdOrdenEncabezado);
            infoEmbarqueRepo.findById(dto.getIdInfoEmbarque()).ifPresent(entity::setIdInfoEmbarque);
            aduanaRepo.findById(dto.getIdAduana()).ifPresent(entity::setIdAduana);
            transporteRepo.findById(dto.getIdTransporte()).ifPresent(entity::setIdTransporte);
            recoleccionRepo.findById(dto.getIdRecoleccion()).ifPresent(entity::setIdRecoleccion);
            cargosRepo.findById(dto.getIdCargos()).ifPresent(entity::setIdCargos);
            financiamientoRepo.findById(dto.getIdFinanciamiento()).ifPresent(entity::setIdFinanciamiento);
            observacionesRepo.findById(dto.getIdObservaciones()).ifPresent(entity::setIdObservaciones);

            repo.save(entity);
            return "Orden de servicio actualizada correctamente";
        } else {
            return "Orden de servicio no encontrada";
        }
    }

    // PATCH - Actualización parcial
    public String patch(Long id, DTOOrdenServicio dto) {
        Optional<OrdenServicioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            OrdenServicioEntity entity = optional.get();

            if (dto.getClienteNIT() != null) entity.setClienteNIT(dto.getClienteNIT());
            if (dto.getIdOrdenEncabezado() != null)
                ordenEncabezadoRepo.findById(dto.getIdOrdenEncabezado()).ifPresent(entity::setIdOrdenEncabezado);
            if (dto.getIdInfoEmbarque() != null)
                infoEmbarqueRepo.findById(dto.getIdInfoEmbarque()).ifPresent(entity::setIdInfoEmbarque);
            if (dto.getIdAduana() != null)
                aduanaRepo.findById(dto.getIdAduana()).ifPresent(entity::setIdAduana);
            if (dto.getIdTransporte() != null)
                transporteRepo.findById(dto.getIdTransporte()).ifPresent(entity::setIdTransporte);
            if (dto.getIdRecoleccion() != null)
                recoleccionRepo.findById(dto.getIdRecoleccion()).ifPresent(entity::setIdRecoleccion);
            if (dto.getIdCargos() != null)
                cargosRepo.findById(dto.getIdCargos()).ifPresent(entity::setIdCargos);
            if (dto.getIdFinanciamiento() != null)
                financiamientoRepo.findById(dto.getIdFinanciamiento()).ifPresent(entity::setIdFinanciamiento);
            if (dto.getIdObservaciones() != null)
                observacionesRepo.findById(dto.getIdObservaciones()).ifPresent(entity::setIdObservaciones);

            repo.save(entity);
            return "Orden de servicio actualizada parcialmente.";
        }
        return "Orden de servicio no encontrada.";
    }

    // DELETE - Eliminar
    public String delete(Long id) {
        Optional<OrdenServicioEntity> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Orden de servicio eliminada correctamente";
        } else {
            return "Orden de servicio no encontrada";
        }
    }
}
