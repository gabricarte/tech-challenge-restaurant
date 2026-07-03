package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.dto.request.UserRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.UserResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.exception.InvalidLoginException;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.exception.UserNotFoundException;
import br.com.techchallenge.restaurant.mapper.UserMapper;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setName("Test Owner");
        owner.setLogin("owner");
        owner.setPassword("pass123");
        owner.setEmail("owner@test.com");
    }

    @Test
    void testValidarLogin_CorrectCredentials_Success() {
        when(ownerRepository.findByLogin("owner")).thenReturn(Optional.of(owner));

        userService.validarLogin("owner", "pass123");

        verify(ownerRepository, times(1)).findByLogin("owner");
    }

    @Test
    void testValidarLogin_WrongPassword_ThrowsException() {
        when(ownerRepository.findByLogin("owner")).thenReturn(Optional.of(owner));

        assertThatThrownBy(() -> userService.validarLogin("owner", "wrongpass"))
                .isInstanceOf(InvalidLoginException.class);
    }

    @Test
    void testValidarLogin_NonExistentLogin_ThrowsException() {
        when(ownerRepository.findByLogin("notfound")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.validarLogin("notfound", "any"))
                .isInstanceOf(InvalidLoginException.class);
    }

    @Test
    void testAtualizarDados_Success() {

        UserRequestDTO dto = new UserRequestDTO(
                "Updated",
                "updated@test.com",
                "pass",
                "newaddr"
        );

        UserResponseDTO responseDTO = new UserResponseDTO(
                1L,
                "Updated",
                "updated@test.com"
        );

        when(ownerRepository.findById(1L))
                .thenReturn(Optional.of(owner));

        when(ownerRepository.save(any()))
                .thenReturn(owner);

        when(userMapper.toDTO(owner))
                .thenReturn(responseDTO);

        UserResponseDTO result =
                userService.atualizarDados(1L, dto);

        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void testAtualizarDados_UserNotFound_ThrowsException() {
        UserRequestDTO dto = new UserRequestDTO("Updated", "updated@test.com", "pass", "newaddr");

        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.atualizarDados(999L, dto))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testTrocarSenha_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any())).thenReturn(owner);

        userService.trocarSenha(1L, "newpass");

        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void testTrocarSenha_NotFound_ThrowsException() {
        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.trocarSenha(999L, "newpass"))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testBuscarPorId_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        Owner result = userService.buscarPorId(1L);

        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_NotFound_ThrowsException() {
        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.buscarPorId(999L))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testDelete_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).delete(any());

        userService.delete(1L);

        verify(ownerRepository, times(1)).delete(owner);
    }
}