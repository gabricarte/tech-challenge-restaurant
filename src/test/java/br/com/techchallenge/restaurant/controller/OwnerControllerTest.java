package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerControllerTest {

    @Test
    void testSave() {

        OwnerService ownerService = mock(OwnerService.class);

        OwnerController controller = new OwnerController(ownerService);

        OwnerRequestDTO request = new OwnerRequestDTO(
                "Owner Test",
                "owner@test.com",
                "owner",
                "123456",
                "Rua Teste",
                LocalDateTime.now()
        );

        OwnerResponseDTO response = new OwnerResponseDTO(
                1L,
                "Owner Test",
                "owner@test.com",
                "Rua Teste",
                LocalDateTime.now()
        );

        when(ownerService.save(request)).thenReturn(response);

        ResponseEntity<OwnerResponseDTO> result = controller.save(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().id());

        verify(ownerService, times(1)).save(request);
    }
}