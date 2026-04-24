package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Customer;
import br.com.techchallenge.restaurant.domain.dto.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.CustomerResponseDTO;
import br.com.techchallenge.restaurant.mapper.CustomerMapper;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
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
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return customerMapper.toDTO(customer);
    }

    @Transactional
    public CustomerResponseDTO atualizarDados(Long id, CustomerRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setBirthDate(dto.birthDate());

        return customerMapper.toDTO(customerRepository.save(customer));
    }
}