package br.com.techchallenge.restaurant.util;

import br.com.techchallenge.restaurant.exception.EmailAlreadyExistsException;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private UserValidator userValidator;

    @Test
    void testValidateEmailDuplicated_EmailDoesNotExist_Success() {
        String email = "teste@email.com";

        when(ownerRepository.existsByEmail(email)).thenReturn(false);
        when(customerRepository.existsByEmail(email)).thenReturn(false);

        assertThatCode(() -> userValidator.validateEmailDuplicated(email))
                .doesNotThrowAnyException();

        verify(ownerRepository, times(1)).existsByEmail(email);
        verify(customerRepository, times(1)).existsByEmail(email);
    }

    @Test
    void testValidateEmailDuplicated_EmailExistsInOwner_ThrowsException() {
        String email = "teste@email.com";

        when(ownerRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> userValidator.validateEmailDuplicated(email))
                .isInstanceOf(EmailAlreadyExistsException.class);

        verify(ownerRepository, times(1)).existsByEmail(email);
        verify(customerRepository, never()).existsByEmail(anyString());
    }

    @Test
    void testValidateEmailDuplicated_EmailExistsInCustomer_ThrowsException() {
        String email = "teste@email.com";

        when(ownerRepository.existsByEmail(email)).thenReturn(false);
        when(customerRepository.existsByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> userValidator.validateEmailDuplicated(email))
                .isInstanceOf(EmailAlreadyExistsException.class);

        verify(ownerRepository, times(1)).existsByEmail(email);
        verify(customerRepository, times(1)).existsByEmail(email);
    }
}
