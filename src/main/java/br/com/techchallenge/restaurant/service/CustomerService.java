package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.*;
import br.com.techchallenge.restaurant.domain.entity.Customer;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.mapper.CustomerMapper;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
        userService.verifyEmailAlreadyExists(dto.email());

        Customer customer = customerMapper.toEntity(dto);

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return customerMapper.toDTO(customer);
    }

    public List<CustomerResponseDTO> findByName(String name) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);

        if (customers.isEmpty()) {
            throw new UserNotFoundException();
        }

        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerResponseDTO update(Long id, CustomerUpdateRequestDTO dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        customer.setAddress(dto.address());
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setCpf(dto.cpf());
        customer.setLogin(dto.login());
        customer.setTelephone(dto.telephone());
        customer.setBirthDate(dto.birthDate());

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    @Transactional
    public void updatePassword(Long id, PasswordUpdateDTO passwordUpdateDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        customer.setPassword(passwordUpdateDTO.newPassword());

        customerRepository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        customerRepository.delete(customer);
    }

    public CustomerResponseDTO login(UserLoginDTO userLoginDTO) {
        Customer customer = customerRepository.findByLogin(userLoginDTO.login()).orElseThrow(InvalidLoginException::new);

        if (!customer.getPassword().equals(userLoginDTO.password())) {
            throw new InvalidLoginException();
        }

        return customerMapper.toDTO(customer);
    }
}