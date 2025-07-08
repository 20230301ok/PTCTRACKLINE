package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.TransporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteRepository extends JpaRepository<TransporteEntity, Long> {
}
