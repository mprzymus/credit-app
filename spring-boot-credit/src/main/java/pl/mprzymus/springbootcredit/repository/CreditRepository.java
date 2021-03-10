package pl.mprzymus.springbootcredit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.springbootcredit.model.Credit;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Long> {
}
