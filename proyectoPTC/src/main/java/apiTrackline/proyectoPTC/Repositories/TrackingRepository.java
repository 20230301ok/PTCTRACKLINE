package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {
}
