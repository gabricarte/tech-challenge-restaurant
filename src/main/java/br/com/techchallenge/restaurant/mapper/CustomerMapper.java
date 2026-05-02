package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.CustomerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getLogin(),
                customer.getCpf(),
                customer.getTelephone(),
                customer.getName(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getLastUpdate()
        );
    }

    public Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setLogin(dto.login());
        customer.setPassword(dto.password());
        customer.setBirthDate(dto.birthDate());
        customer.setAddress(dto.address());
        customer.setCpf(dto.cpf());
        customer.setTelephone(dto.telephone());
        return customer;
    }
}