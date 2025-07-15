package apiTrackline.proyectoPTC.Services;

import apiTrackline.proyectoPTC.Entities.RecoleccionEntity;
import apiTrackline.proyectoPTC.Models.DTO.DTORecoleccion;
import apiTrackline.proyectoPTC.Repositories.RecoleccionRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecoleccionService {

    @Autowired
    private RecoleccionRepository repo;

    //Método GET
    public List<DTORecoleccion> obtenerRecoleccion(){
        List<RecoleccionEntity> recoleccion = repo.findAll();
        List<DTORecoleccion> collect = recoleccion.stream()
                .map(this::convertirArecoleccionDTO)
                .collect(Collectors.toList());
        return collect;
    }

    private DTORecoleccion convertirArecoleccionDTO(RecoleccionEntity entity){
        DTORecoleccion dto = new DTORecoleccion();
        dto.setIdRecoleccion(entity.getIdRecoleccion());
        dto.setTransporte(entity.getTransporte());
        dto.setRecoleccionEntrega(entity.getRecoleccionEntrega());
        dto.setNumeroDoc(entity.getNumeroDoc());
        dto.setLugarOrigen(entity.getLugarOrigen());
        dto.setPaisOrigen(entity.getPaisOrigen());
        dto.setLugarDestino(entity.getLugarDestino());
        dto.setPaisDestino(entity.getPaisDestino());
        return dto;
    }

    public String agregarRecoleccion(DTORecoleccion dto){
        try{
            RecoleccionEntity entity = new RecoleccionEntity();
            entity.setIdRecoleccion(dto.getIdRecoleccion());
            entity.setTransporte(dto.getTransporte());
            entity.setRecoleccionEntrega(dto.getRecoleccionEntrega());
            entity.setNumeroDoc(dto.getNumeroDoc());
            entity.setLugarOrigen(dto.getLugarOrigen());
            entity.setPaisOrigen(dto.getPaisOrigen());
            entity.setLugarDestino(dto.getLugarDestino());
            entity.setPaisDestino(dto.getPaisDestino());
            repo.save(entity);
            return "Información de recolección agregada correctamente";
        }
        catch (Exception e){
            return "Error al ingresar recoleccion: " + e.getMessage();
        }
    }

    public String actualizarRecoleccion(Long id, DTORecoleccion dto){
        try {
            Optional<RecoleccionEntity> optionalRecoleccion = repo.findById(id);
            if (optionalRecoleccion.isPresent()){

                RecoleccionEntity entity = optionalRecoleccion.get();

                entity.setTransporte(dto.getTransporte());
                entity.setRecoleccionEntrega(dto.getRecoleccionEntrega());
                entity.setNumeroDoc(dto.getNumeroDoc());
                entity.setLugarOrigen(dto.getLugarOrigen());
                entity.setPaisOrigen(dto.getPaisOrigen());
                entity.setLugarDestino(dto.getLugarDestino());
                entity.setPaisDestino(dto.getPaisDestino());
                repo.save(entity);
                return "La información de recolección ha sido actualizada correctamente";
            }
            else {
                return "El registro de recolección no se ha encontrado con id: " + id;
            }
        }
        catch (Exception e){
            return "Error al actualizar aduana: " + e.getMessage();
        }
    }

    public String eliminarRecoleccion(Long id){
        Optional<RecoleccionEntity> recoleccion = repo.findById(id);
        if (recoleccion.isPresent()){
            repo.deleteById(id);
            return "La información de recolección ha sido eliminada correctamente";
        }
        else {
            return "No se ha encontrado información de recolección con el id: " + id;
        }
    }

    public String actualizarParcialmenteRecoleccion(Long id, DTORecoleccion dto){
        try{
            Optional<RecoleccionEntity> optRecoleccion = repo.findById(id);
            if (optRecoleccion.isPresent()){
                RecoleccionEntity entity = optRecoleccion.get();

                if (dto.getTransporte() != null){
                    entity.setTransporte(dto.getTransporte());
                }
                if (dto.getRecoleccionEntrega() != null){
                    entity.setRecoleccionEntrega(dto.getRecoleccionEntrega());
                }
                if(dto.getNumeroDoc() != null){
                    entity.setNumeroDoc(dto.getNumeroDoc());
                }
                if(dto.getLugarOrigen() != null){
                    entity.setLugarOrigen(dto.getLugarOrigen());
                }
                if (dto.getPaisOrigen() != null){
                    entity.setPaisOrigen(dto.getPaisOrigen());
                }
                if(dto.getLugarDestino() != null){
                    entity.setLugarDestino(dto.getLugarDestino());
                }
                if (dto.getPaisDestino() != null){
                    entity.setPaisDestino(dto.getPaisDestino());
                }
                repo.save(entity);
                return "Se ha actualizado correctamente la información de recolección";
            }
            else {
                return "No se ha encontrado información de recolección con id: " + id;
            }
        } catch (Exception e) {
            return "Ha ocurrido un error al momento de actualizar parcialmente el registro: " + e.getMessage();
        }
    }
}
