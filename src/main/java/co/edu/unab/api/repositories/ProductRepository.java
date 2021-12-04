package co.edu.unab.api.repositories;

import co.edu.unab.api.models.ClienteModel;
import co.edu.unab.api.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
    
      

}

