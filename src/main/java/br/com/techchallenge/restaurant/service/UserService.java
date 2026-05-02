package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.exception.EmailAlreadyExistsException;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OwnerRepository ownerRepository;

    private final CustomerRepository customerRepository;

    public  void verifyEmailAlreadyExists(String email) {
        boolean emailExistsInOwners = ownerRepository.existsByEmail(email);
        boolean emailExistsInClients = customerRepository.existsByEmail(email);

        if (emailExistsInOwners || emailExistsInClients) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}
