package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController controller;

    private CustomerRequestDTO requestDTO;
    private CustomerResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CustomerRequestDTO("Cliente", "client@test.com", "123", "addr", "999", "cpf", "pass", LocalDate.of(1990, 5, 15));
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                1L,
                "client123",
                "12345678900",
                "11999999999",
                "Cliente",
                "client@test.com",
                LocalDate.of(1990, 5, 15),
                LocalDateTime.now()
        );
    }
        @Test
        void testSave_Success() {
            when(customerService.save(any())).thenReturn(responseDTO);

            ResponseEntity<CustomerResponseDTO> result = controller.save(requestDTO);

            assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(result.getBody()).isNotNull();
            assertThat(result.getBody().id()).isEqualTo(1L);
            verify(customerService, times(1)).save(any());
        }
    }