package pl.mprzymus.springbootcustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.mprzymus.springbootcustomer.model.Customer;
import pl.mprzymus.springbootcustomer.repository.CustomerRepository;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("h2_db")
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CustomerRepository customerRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Data init started");
        var customer = new Customer();
        customer.setPesel("11111111111");
        customer.setFirstName("Name");
        customer.setSurname("Surname");
        customer.setCreditId(2L);

        customerRepository.save(customer);
        log.info("Data init done");
    }
}
