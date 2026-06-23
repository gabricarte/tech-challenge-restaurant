package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.CustomerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.CustomerResponseDTO;
import br.com.techchallenge.restaurant.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Test
    void testSave() {

        CustomerService customerService = mock(CustomerService.class);

        CustomerController controller =
                new CustomerController(customerService);

        CustomerRequestDTO request = new CustomerRequestDTO(
                "Cliente Teste",
                "cliente@test.com",
                "123456",
                "Rua Teste",
                "11999999999",
                "12345678900",
                "password123",
                LocalDate.of(1990, 5, 15)

        );

        CustomerResponseDTO response = new CustomerResponseDTO(
                1L,
                "Cliente Teste",
                "cliente@test.com",
                "11999999999",
                LocalDate.of(1990, 5, 15)
        );

        when(customerService.save(request))
                .thenReturn(response);

        ResponseEntity<CustomerResponseDTO> result =
                controller.save(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().id());

        verify(customerService, times(1)).save(request);
    }
}