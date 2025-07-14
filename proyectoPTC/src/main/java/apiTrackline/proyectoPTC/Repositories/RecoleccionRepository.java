package apiTrackline.proyectoPTC.Repositories;

import apiTrackline.proyectoPTC.Entities.RecoleccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoleccionRepository extends JpaRepository<RecoleccionEntity, Long> {
    boolean existByNumeroDoc(String numeroDoc); //Verifica que no haya un numero de documento igual en la base de datsos
    boolean existByNumeroDocAndId(String numeroDoc, Long idRecoleccion);  //verificar si existe otro numero de documento igual, pero con un ID diferente.
}
