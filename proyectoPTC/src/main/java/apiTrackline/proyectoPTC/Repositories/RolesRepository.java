package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
}
