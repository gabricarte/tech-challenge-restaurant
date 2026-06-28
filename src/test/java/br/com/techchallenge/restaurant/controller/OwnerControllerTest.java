package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController controller;

    private OwnerRequestDTO requestDTO;
    private OwnerResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new OwnerRequestDTO("Owner", "owner@test.com", "login", "pass", "addr", LocalDateTime.now());
        responseDTO = new OwnerResponseDTO(1L, "Owner", "owner@test.com", "addr", LocalDateTime.now(), new UserType(1L, "Dono de Restaurante"));
    }

    @Test
    void testSave_Success() {
        when(ownerService.save(any())).thenReturn(responseDTO);

        ResponseEntity<OwnerResponseDTO> result = controller.save(requestDTO);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().id()).isEqualTo(1L);
        verify(ownerService, times(1)).save(any());
    }

    @Test
    void testFindAll_Success() {
        when(ownerService.findAll()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<OwnerResponseDTO>> result = controller.findAll();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void testFindById_Success() {
        when(ownerService.findById(1L)).thenReturn(responseDTO);

        ResponseEntity<OwnerResponseDTO> result = controller.findById(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        verify(ownerService, times(1)).findById(1L);
    }

    @Test
    void testAtualizar_Success() {
        when(ownerService.atualizarDados(eq(1L), any())).thenReturn(responseDTO);

        ResponseEntity<OwnerResponseDTO> result = controller.atualizar(1L, requestDTO);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        verify(ownerService, times(1)).atualizarDados(eq(1L), any());
    }

    @Test
    void testTrocarSenha_Success() {
        doNothing().when(ownerService).trocarSenha(1L, "newPass");

        ResponseEntity<Void> result = controller.trocarSenha(1L, "newPass");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(ownerService, times(1)).trocarSenha(1L, "newPass");
    }
}