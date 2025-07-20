package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.TipoDatoContableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDatoContableRepository extends JpaRepository<TipoDatoContableEntity, Long> {
}
