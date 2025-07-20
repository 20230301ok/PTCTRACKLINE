package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.OrdenServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicioEntity, Long> {
}

