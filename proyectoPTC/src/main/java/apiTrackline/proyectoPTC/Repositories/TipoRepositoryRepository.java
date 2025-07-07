package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.TipoServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Indica que hay in repositorio de métodos

//Heredamos todo lo de JPARepository
public interface TipoRepositoryRepository extends JpaRepository<TipoServicioEntity, Long>{
}
