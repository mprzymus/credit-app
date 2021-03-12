package pl.mprzymus.springbootcredit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.repository.CreditRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    public static final long ID = 1L;
    public static final String CREDIT_NAME = "Name";
    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @Test
    void saveTest() {
        var credit = new Credit();
        credit.setId(ID);
        when(creditRepository.save(any())).thenReturn(credit);

        var saved = creditService.saveCredit(CREDIT_NAME);

        assertEquals(ID, saved.getId());
        verify(creditRepository).save(any());
    }

    @Test
    void getCreditsTest() {
        creditService.findAllCredits();

        verify(creditRepository).findAll();
    }
}