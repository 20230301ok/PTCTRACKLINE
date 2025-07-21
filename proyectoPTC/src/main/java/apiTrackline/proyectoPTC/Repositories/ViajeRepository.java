package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.ViajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<ViajeEntity, Long> {
}
