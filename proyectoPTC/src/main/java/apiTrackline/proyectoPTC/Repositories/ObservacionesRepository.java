package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.ObservacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservacionesRepository extends JpaRepository<ObservacionesEntity, Long> {
}

