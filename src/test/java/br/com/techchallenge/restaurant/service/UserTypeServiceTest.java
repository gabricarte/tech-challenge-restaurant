package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.domain.enums.UserTypeEnum;
import br.com.techchallenge.restaurant.exception.UserTypeNotFoundException;
import br.com.techchallenge.restaurant.repository.UserTypeRepository;
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
class UserTypeServiceTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private UserTypeService userTypeService;

    private UserType userType;

    @BeforeEach
    void setUp() {
        userType = new UserType();
        userType.setId(UserTypeEnum.OWNER.getId());
        userType.setName(UserTypeEnum.OWNER.getName());
    }

    @Test
    void testCreate_Success() {
        when(userTypeRepository.save(any())).thenReturn(userType);

        UserType result = userTypeService.create(userType);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(UserTypeEnum.OWNER.getId());

        verify(userTypeRepository, times(1)).save(any());
    }

    @Test
    void testFindAll_ReturnsUserTypes() {
        when(userTypeRepository.findAll()).thenReturn(List.of(userType));

        List<UserType> result = userTypeService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName())
                .isEqualTo(UserTypeEnum.OWNER.getName());
    }

    @Test
    void testFindById_Success() {
        when(userTypeRepository.findById(UserTypeEnum.OWNER.getId()))
                .thenReturn(Optional.of(userType));

        UserType result = userTypeService.findById(UserTypeEnum.OWNER.getId());

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(UserTypeEnum.OWNER.getId());
        assertThat(result.getName()).isEqualTo(UserTypeEnum.OWNER.getName());
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(userTypeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userTypeService.findById(999L))
                .isInstanceOf(UserTypeNotFoundException.class);
    }

    @Test
    void testUpdate_Success() {
        UserType updated = new UserType();
        updated.setName(UserTypeEnum.CUSTOMER.getName());

        when(userTypeRepository.findById(UserTypeEnum.OWNER.getId()))
                .thenReturn(Optional.of(userType));
        when(userTypeRepository.save(any())).thenReturn(userType);

        UserType result = userTypeService.update(
                UserTypeEnum.OWNER.getId(),
                updated
        );

        assertThat(result).isNotNull();
        assertThat(result.getName())
                .isEqualTo(UserTypeEnum.CUSTOMER.getName());

        verify(userTypeRepository, times(1)).save(userType);
    }

    @Test
    void testUpdate_NotFound_ThrowsException() {
        UserType updated = new UserType();
        updated.setName(UserTypeEnum.CUSTOMER.getName());

        when(userTypeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userTypeService.update(999L, updated))
                .isInstanceOf(UserTypeNotFoundException.class);
    }

    @Test
    void testDelete_Success() {
        when(userTypeRepository.findById(UserTypeEnum.OWNER.getId()))
                .thenReturn(Optional.of(userType));
        doNothing().when(userTypeRepository).delete(userType);

        userTypeService.delete(UserTypeEnum.OWNER.getId());

        verify(userTypeRepository, times(1)).delete(userType);
    }

    @Test
    void testDelete_NotFound_ThrowsException() {
        when(userTypeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userTypeService.delete(999L))
                .isInstanceOf(UserTypeNotFoundException.class);
    }
}
