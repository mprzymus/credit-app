package pl.mprzymus.springbootcustomer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.springbootcustomer.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
