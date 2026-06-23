package br.com.techchallenge.restaurant.mapper;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper();

    @Test
    void testToDTO_MapsAllFields() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLogin("johndoe");
        customer.setName("John Doe");
        customer.setEmail("john@test.com");
        customer.setCpf("12345678900");
        customer.setBirthDate(LocalDate.of(1990, 5, 15));

        CustomerResponseDTO dto = mapper.toDTO(customer);

        assertThat(dto.id()).isEqualTo(1L);

        assertThat(dto.login()).isEqualTo("John Doe");
        assertThat(dto.name()).isEqualTo("john@test.com");
        assertThat(dto.email()).isEqualTo("12345678900");

        assertThat(dto.birthDate())
                .isEqualTo(LocalDate.of(1990, 5, 15));
    }

    @Test
    void testToEntity_MapsAllFields() {

        CustomerRequestDTO dto = new CustomerRequestDTO(
                "Jane",
                "jane@test.com",
                "12345678901",
                "login123",
                "999999999",
                "addr123",
                "pass",
                LocalDate.of(1995, 3, 20)
        );

        Customer customer = mapper.toEntity(dto);

        assertThat(customer.getName()).isEqualTo("Jane");
        assertThat(customer.getEmail()).isEqualTo("jane@test.com");
        assertThat(customer.getLogin()).isEqualTo("login123");
        assertThat(customer.getPassword()).isEqualTo("pass");
        assertThat(customer.getBirthDate()).isEqualTo(LocalDate.of(1995, 3, 20));
        assertThat(customer.getAddress()).isEqualTo("addr123");
        assertThat(customer.getCpf()).isEqualTo("12345678901");
        assertThat(customer.getTelephone()).isEqualTo("999999999");
    }
}