package pl.mprzymus.springbootproduct.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.springbootproduct.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
