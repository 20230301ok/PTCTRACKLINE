package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.SelectivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectivoRepository extends JpaRepository<SelectivoEntity, Long> {
}
