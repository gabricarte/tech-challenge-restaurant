package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Customer;
import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.mapper.CustomerMapper;
import br.com.techchallenge.restaurant.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer 1");
    }

    @Test
    void testSave_ValidCustomer_Success() {
        CustomerRequestDTO dto = new CustomerRequestDTO("C1", "c@test.com", "123", "login", "99999", "addr", "pass", null);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(1L, "login", "C1", "c@test.com", null);

        when(customerMapper.toEntity(any())).thenReturn(customer);
        when(customerRepository.save(any())).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.save(dto);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        verify(customerRepository, times(1)).save(any());
        verify(customerMapper, times(1)).toEntity(any());
    }

    @Test
    void testFindAll_ReturnsCustomers() {
        CustomerResponseDTO dto = new CustomerResponseDTO(1L, "login", "C1", "leticia@email.com", null);
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        List<CustomerResponseDTO> result = customerService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("C1");
    }

    @Test
    void testFindAll_EmptyList_ReturnsEmpty() {
        when(customerRepository.findAll()).thenReturn(List.of());

        List<CustomerResponseDTO> result = customerService.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void testFindById_Success() {
        CustomerResponseDTO dto = new CustomerResponseDTO(1L, "login", "C1", "leticia@email.com", null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        CustomerResponseDTO result = customerService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("C1");
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.findById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Cliente não encontrado");
    }

    @Test
    void testAtualizarDados_Success() {
        CustomerRequestDTO dto = new CustomerRequestDTO("Updated", "updated@test.com", "123", "login", "99999", "addr", "pass", null);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(1L, "login", "Updated", "updated@test.com", null);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.atualizarDados(1L, dto);

        assertThat(result).isNotNull();
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testAtualizarDados_NotFound_ThrowsException() {
        CustomerRequestDTO dto = new CustomerRequestDTO("Updated", "updated@test.com", "123", "login", "99999", "addr", "pass", null);
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.atualizarDados(999L, dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Cliente não encontrado");
    }
}