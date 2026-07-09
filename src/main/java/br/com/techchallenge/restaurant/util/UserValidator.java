package br.com.techchallenge.restaurant.util;

import br.com.techchallenge.restaurant.exception.EmailAlreadyExistsException;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {
    private final OwnerRepository ownerRepository;
    private final CustomerRepository customerRepository;

    public void validateEmailDuplicated(String email) {
        if (ownerRepository.existsByEmail(email) || customerRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}
