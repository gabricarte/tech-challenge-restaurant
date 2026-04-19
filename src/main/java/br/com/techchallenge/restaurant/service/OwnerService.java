package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(id));
    }

    @Transactional
    public Owner atualizarDados(Long id, Owner novosDados) {
        Owner owner = findById(id);
        owner.setName(novosDados.getName());
        owner.setEmail(novosDados.getEmail());
        owner.setAddress(novosDados.getAddress());

        return ownerRepository.save(owner);
    }
}