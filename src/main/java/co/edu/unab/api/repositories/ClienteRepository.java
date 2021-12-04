package co.edu.unab.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unab.api.models.ClienteModel;

import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteModel , String> {

    List<ClienteModel> findAllByNombre(String nombre);
    List<ClienteModel> findAllByNombreAndApellido(String nombre,String apellido);
    List<ClienteModel> findAllByAddress_Ciudad(String ciudad);
    //    Despues = Mayores
    List<ClienteModel> findAllByPuntosGreaterThanEqual(Long puntos);
    //    Antes = Menores
    List<ClienteModel> findAllByPuntosLessThanEqual(Long puntos);

}

