package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerUpdateRequestDTO;
import br.com.techchallenge.restaurant.domain.entity.Customer;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.domain.enums.UserTypeEnum;
import br.com.techchallenge.restaurant.exception.CustomerNotFoundException;
import br.com.techchallenge.restaurant.exception.UserTypeNotFoundException;
import br.com.techchallenge.restaurant.mapper.CustomerMapper;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import br.com.techchallenge.restaurant.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserTypeRepository userTypeRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO save(CustomerUpdateRequestDTO dto) {
        Customer customer = customerMapper.toEntity(dto);

        Long customerTypeId = UserTypeEnum.CUSTOMER.getId();

        UserType customerType = userTypeRepository.findById(customerTypeId)
                .orElseThrow(() -> new UserTypeNotFoundException(customerTypeId));

        customer.setUserType(customerType);

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return customerMapper.toDTO(customer);
    }

    @Transactional
    public CustomerResponseDTO updateData(Long id, CustomerUpdateRequestDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setBirthDate(dto.birthDate());

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customer);
    }
}