package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Customer;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.mapper.CustomerMapper;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import br.com.techchallenge.restaurant.repository.UserTypeRepository;
import br.com.techchallenge.restaurant.util.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setId(1L);
        customer.setLogin("login");
        customer.setCpf("123");
        customer.setTelephone("99999");
        customer.setName("Customer 1");
        customer.setEmail("c@test.com");
        customer.setBirthDate(LocalDate.of(1990, 5, 15));
    }

    @Test
    void testSave_ValidCustomer_Success() {

        CustomerRequestDTO dto = new CustomerRequestDTO(
                "Customer 1",
                "c@test.com",
                "123",
                "login",
                "99999",
                "addr",
                "pass",
                LocalDate.of(1990,5,15)
        );

        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                1L,
                "login",
                "123",
                "99999",
                "Customer 1",
                "c@test.com",
                LocalDate.of(1990,5,15),
                null
        );

        when(customerMapper.toEntity(any())).thenReturn(customer);
        when(customerRepository.save(any())).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(responseDTO);
        when(userTypeRepository.findById(any())).thenReturn(Optional.of(new UserType(2L, "Cliente")));

        CustomerResponseDTO result = customerService.save(dto);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);

        verify(customerRepository).save(any());
    }

    @Test
    void testFindAll_ReturnsCustomers() {

        CustomerResponseDTO dto = new CustomerResponseDTO(
                1L,
                "login",
                "123",
                "99999",
                "Customer 1",
                "c@test.com",
                LocalDate.of(1990, 5, 15),
                null
        );

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        List<CustomerResponseDTO> result = customerService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("Customer 1");
    }

    @Test
    void testFindAll_EmptyList() {

        when(customerRepository.findAll()).thenReturn(List.of());

        List<CustomerResponseDTO> result = customerService.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void testFindById() {

        CustomerResponseDTO dto = new CustomerResponseDTO(
                1L,
                "login",
                "123",
                "99999",
                "Customer 1",
                "c@test.com",
                LocalDate.of(1990,5,15),
                null
        );

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        CustomerResponseDTO result = customerService.findById(1L);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("Customer 1");
    }

    @Test
    void testFindById_NotFound() {

        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.findById(999L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testAtualizarDados() {

        CustomerRequestDTO dto = new CustomerRequestDTO(
                "Updated",
                "updated@test.com",
                "321",
                "login",
                "88888",
                "addr",
                "pass",
                LocalDate.of(1995,1,1)
        );

        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                1L,
                "login",
                "321",
                "88888",
                "Updated",
                "updated@test.com",
                LocalDate.of(1995,1,1),
                LocalDateTime.now()
        );

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.updateData(1L, dto);

        assertThat(result).isNotNull();

        verify(customerRepository).save(any());
    }

    @Test
    void testAtualizarDados_NotFound() {

        CustomerRequestDTO dto = new CustomerRequestDTO(
                "Updated",
                "updated@test.com",
                "321",
                "login",
                "88888",
                "addr",
                "pass",
                null
        );

        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.updateData(999L, dto))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDelete_Success() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(any());

        customerService.delete(1L);

        verify(customerRepository, times(1)).delete(customer);
    }
}