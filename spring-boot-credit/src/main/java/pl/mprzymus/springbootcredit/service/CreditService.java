package pl.mprzymus.springbootcredit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.repository.CreditRepository;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    public Credit saveIfExists(String credit) {
        var found = creditRepository.findByCreditName(credit);
        if (found.isEmpty()) {
            var toSave = new Credit();
            toSave.setCreditName(credit);
            return creditRepository.save(toSave);
        }
        return found.get();
    }
}
