package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.request.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.mapper.UserMapper;
import br.com.techchallenge.restaurant.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController controller;

    private UserRequestDTO requestDTO;
    private UserResponseDTO responseDTO;
    private Owner owner;

    @BeforeEach
    void setUp() {
        requestDTO = new UserRequestDTO("User", "user@test.com", "pass123", "addr");
        responseDTO = new UserResponseDTO(1L, "User", "user@test.com");
        owner = new Owner();
        owner.setId(1L);
        owner.setName("User");
    }

    @Test
    void testLogin_Success() {
        Map<String, String> body = Map.of("login", "admin", "password", "123");
        doNothing().when(userService).validarLogin("admin", "123");

        ResponseEntity<Void> response = controller.login(body);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService, times(1)).validarLogin("admin", "123");
    }

    @Test
    void testFindById_Success() {
        when(userService.buscarPorId(1L)).thenReturn(owner);
        when(userMapper.toDTO(owner)).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> response = controller.findById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        verify(userService, times(1)).buscarPorId(1L);
    }

    @Test
    void testUpdate_Success() {
        when(userService.atualizarDados(eq(1L), any())).thenReturn(responseDTO);

        ResponseEntity<UserResponseDTO> response = controller.update(1L, requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        verify(userService, times(1)).atualizarDados(eq(1L), any());
    }

    @Test
    void testChangePassword_Success() {
        Map<String, String> body = Map.of("newPassword", "newPass123");
        doNothing().when(userService).trocarSenha(1L, "newPass123");

        ResponseEntity<Void> response = controller.changePassword(1L, body);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(userService, times(1)).trocarSenha(1L, "newPass123");
    }
}