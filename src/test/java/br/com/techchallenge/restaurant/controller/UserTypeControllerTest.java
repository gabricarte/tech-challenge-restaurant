package br.com.techchallenge.restaurant.controller;
import br.com.techchallenge.restaurant.domain.entity.UserType;
import br.com.techchallenge.restaurant.service.UserTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTypeControllerTest {

    @Mock
    private UserTypeService userTypeService;

    @InjectMocks
    private UserTypeController controller;

    private UserType userType;

    @BeforeEach
    void setUp() {
        userType = new UserType(1L, "Administrador");
    }

    @Test
    void testCreate_Success() {
        when(userTypeService.create(any())).thenReturn(userType);

        ResponseEntity<UserType> result = controller.create(userType);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo(1L);

        verify(userTypeService, times(1)).create(any());
    }

    @Test
    void testFindAll_Success() {
        when(userTypeService.findAll()).thenReturn(List.of(userType));

        ResponseEntity<List<UserType>> result = controller.findAll();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);

        verify(userTypeService, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(userTypeService.findById(1L)).thenReturn(userType);

        ResponseEntity<UserType> result = controller.findById(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo(1L);

        verify(userTypeService, times(1)).findById(1L);
    }

    @Test
    void testUpdate_Success() {
        when(userTypeService.update(eq(1L), any())).thenReturn(userType);

        ResponseEntity<UserType> result = controller.update(1L, userType);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isEqualTo(1L);

        verify(userTypeService, times(1)).update(eq(1L), any());
    }

    @Test
    void testDelete_Success() {
        doNothing().when(userTypeService).delete(1L);

        ResponseEntity<Void> result = controller.delete(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        verify(userTypeService, times(1)).delete(1L);
    }
}