package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.ServicioTransporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioTransporteRepository extends JpaRepository<ServicioTransporteEntity, Long> {
}
