package pl.mprzymus.springbootcredit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.springbootcredit.model.Credit;

import java.util.Optional;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Long> {
    Optional<Credit> findByCreditName(String creditName);
}
