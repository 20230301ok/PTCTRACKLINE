package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.AduanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AduanaRepository extends JpaRepository<AduanaEntity, Long> {
}
