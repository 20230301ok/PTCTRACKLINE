package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.FinanciamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanciamientoRepository extends JpaRepository<FinanciamientoEntity, Long> {
}
