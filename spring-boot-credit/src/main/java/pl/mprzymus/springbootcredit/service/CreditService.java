package pl.mprzymus.springbootcredit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.repository.CreditRepository;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    public Credit saveCredit(String creditName) {
        Credit credit = createCreditWithName(creditName);
        return creditRepository.save(credit);
    }

    private Credit createCreditWithName(String creditName) {
        var credit = new Credit();
        credit.setCreditName(creditName);
        return credit;
    }

    public Iterable<Credit> findAllCredits() {
        return creditRepository.findAll();
    }
}
