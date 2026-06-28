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
        responseDTO = new CustomerResponseDTO(1L, "Cliente", "client@test.com", "addr", LocalDate.of(1990, 5, 15));
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

    @Test
    void testFindAll_Success() {
        when(customerService.findAll()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<CustomerResponseDTO>> result = controller.findAll();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void testFindById_Success() {
        when(customerService.findById(1L)).thenReturn(responseDTO);

        ResponseEntity<CustomerResponseDTO> result = controller.findById(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        verify(customerService, times(1)).findById(1L);
    }

    @Test
    void testAtualizar_Success() {
        when(customerService.atualizarDados(eq(1L), any())).thenReturn(responseDTO);

        ResponseEntity<CustomerResponseDTO> result = controller.atualizar(1L, requestDTO);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        verify(customerService, times(1)).atualizarDados(eq(1L), any());
    }
}