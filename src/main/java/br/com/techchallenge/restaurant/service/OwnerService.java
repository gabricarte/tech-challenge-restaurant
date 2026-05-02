package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.*;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.mapper.OwnerMapper;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final UserService userService;
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Transactional
    public OwnerResponseDTO save(OwnerRequestDTO dto) {
        userService.verifyEmailAlreadyExists(dto.email());

        Owner owner = new Owner();
        updateEntityFromDTO(owner, dto);

        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.toResponseDTO(savedOwner);
    }

    public List<OwnerResponseDTO> findAll() {
        return ownerRepository.findAll().stream()
                .map(ownerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public OwnerResponseDTO findById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return ownerMapper.toResponseDTO(owner);
    }

    public List<OwnerResponseDTO> findByName(String name) {
        List<Owner> owners = ownerRepository.findByNameContainingIgnoreCase(name);

        if (owners.isEmpty()) {
            throw new UserNotFoundException();
        }

        return owners.stream()
                .map(ownerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OwnerResponseDTO update(Long id, OwnerUpdateRequestDTO dto) {
        Owner owner = ownerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setAddress(dto.address());

        return ownerMapper.toResponseDTO(ownerRepository.save(owner));
    }

    @Transactional
    public void updatePassword(Long id, PasswordUpdateDTO passwordUpdateDTO) {
        Owner owner = ownerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        owner.setPassword(passwordUpdateDTO.newPassword());

        ownerRepository.save(owner);
    }

    public void delete(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(UserNotFoundException::new);
        ownerRepository.delete(owner);
    }

    public OwnerResponseDTO login(UserLoginDTO userLoginDTO) {
        Owner owner = ownerRepository.findByLogin(userLoginDTO.login()).orElseThrow(InvalidLoginException::new);

        if (!owner.getPassword().equals(userLoginDTO.password())) {
            throw new InvalidLoginException();
        }

        return ownerMapper.toResponseDTO(owner);
    }

    private void updateEntityFromDTO(Owner owner, OwnerRequestDTO dto) {
        owner.setName(dto.name());
        owner.setEmail(dto.email());
        owner.setLogin(dto.login());
        owner.setPassword(dto.password());
        owner.setAddress(dto.address());
    }
}