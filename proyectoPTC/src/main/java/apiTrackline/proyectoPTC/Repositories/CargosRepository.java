package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.CargosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends JpaRepository<CargosEntity, Long> {
}
