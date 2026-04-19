package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.dto.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.OwnerResponseDTO;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public OwnerResponseDTO save(OwnerRequestDTO dto) {
        Owner owner = new Owner();
        updateEntityFromDTO(owner, dto);

        Owner savedOwner = ownerRepository.save(owner);
        return toResponseDTO(savedOwner);
    }

    public List<OwnerResponseDTO> findAll() {
        return ownerRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public OwnerResponseDTO findById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(id));
        return toResponseDTO(owner);
    }

    @Transactional
    public OwnerResponseDTO atualizarDados(Long id, OwnerRequestDTO dto) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(id));

        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setAddress(dto.address());

        return toResponseDTO(ownerRepository.save(owner));
    }

    @Transactional
    public void trocarSenha(Long id, String novaSenha) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(id));

        owner.setPassword(novaSenha);
        ownerRepository.save(owner);
    }

    private OwnerResponseDTO toResponseDTO(Owner owner) {
        return new OwnerResponseDTO(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                owner.getAddress(),
                owner.getLastUpdate()
        );
    }

    private void updateEntityFromDTO(Owner owner, OwnerRequestDTO dto) {
        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setLogin(dto.login());
        owner.setPassword(dto.password());
        owner.setAddress(dto.address());
    }
}