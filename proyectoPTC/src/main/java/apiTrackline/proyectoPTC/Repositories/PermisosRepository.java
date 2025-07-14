package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.PermisosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisosRepository extends JpaRepository <PermisosEntity, Long> {
}
