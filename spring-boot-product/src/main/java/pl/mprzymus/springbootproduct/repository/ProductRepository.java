package pl.mprzymus.springbootproduct.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.springbootproduct.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByProductNameAndCreditId(String productName, Long creditId);
}
