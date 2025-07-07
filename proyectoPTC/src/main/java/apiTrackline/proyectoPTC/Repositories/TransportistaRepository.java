package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.TransportistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportistaRepository extends JpaRepository<TransportistaEntity, Long> {
}
