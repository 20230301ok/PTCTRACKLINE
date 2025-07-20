package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, String> {
    boolean existsByUsuario_IdUsuario(Long idUsuario);
}
