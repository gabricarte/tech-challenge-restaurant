package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import br.com.techchallenge.restaurant.domain.dto.request.OwnerRequestDTO;
import br.com.techchallenge.restaurant.domain.dto.response.OwnerResponseDTO;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.exception.OwnerNotFoundException;
import br.com.techchallenge.restaurant.repository.OwnerRepository;
import br.com.techchallenge.restaurant.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private OwnerService ownerService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setName("Owner 1");
        UserType userType = new UserType(1L, "Dono de Restaurante");
        owner.setUserType(userType);
    }

    @Test
    void testSave_ValidOwner_Success() {
        OwnerRequestDTO dto = new OwnerRequestDTO(
                "O1",
                "o@test.com",
                "login",
                "pass",
                "addr",
                LocalDateTime.now()
        );

        UserType userType = new UserType(1L, "Dono de Restaurante");

        when(ownerRepository.save(any())).thenReturn(owner);
        when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));

        OwnerResponseDTO result = ownerService.save(dto);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void testFindAll_ReturnsOwners() {
        when(ownerRepository.findAll()).thenReturn(List.of(owner));

        List<OwnerResponseDTO> result = ownerService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("Owner 1");
    }

    @Test
    void testFindById_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        OwnerResponseDTO result = ownerService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("Owner 1");
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ownerService.findById(999L))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testAtualizarDados_Success() {
        OwnerRequestDTO dto = new OwnerRequestDTO(
                "O1",
                "o@test.com",
                "login",
                "pass",
                "addr",
                LocalDateTime.now()
        );
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any())).thenReturn(owner);
        OwnerResponseDTO result = ownerService.updateData(1L, dto);

        assertThat(result).isNotNull();
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void testAtualizarDados_NotFound_ThrowsException() {
        OwnerRequestDTO dto = new OwnerRequestDTO(
                "O1",
                "o@test.com",
                "login",
                "pass",
                "addr",
                LocalDateTime.now()
        );
        assertThatThrownBy(() -> ownerService.updateData(999L, dto))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testTrocarSenha_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any())).thenReturn(owner);

        ownerService.changePassword(1L, "newpass");

        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void testTrocarSenha_NotFound_ThrowsException() {
        when(ownerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ownerService.changePassword(999L, "newpass"))
                .isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void testDelete_Success() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        doNothing().when(ownerRepository).delete(any());

        ownerService.delete(1L);

        verify(ownerRepository, times(1)).delete(owner);
    }
}

