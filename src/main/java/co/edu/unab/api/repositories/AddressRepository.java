package co.edu.unab.api.repositories;

import co.edu.unab.api.models.AddressModel;
import co.edu.unab.api.models.ClienteModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<AddressModel, String> {
    
      

}

