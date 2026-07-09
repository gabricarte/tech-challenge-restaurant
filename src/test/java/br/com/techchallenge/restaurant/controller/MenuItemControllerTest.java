package br.com.techchallenge.restaurant.controller;


import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.service.MenuItemService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController controller;

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Pizza");
        menuItem.setDescription("Pizza Calabresa");
    }

    @Test
    void testCreate_Success() {
        when(menuItemService.create(any(), eq(1L))).thenReturn(menuItem);

        ResponseEntity<MenuItem> response = controller.create(menuItem, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Pizza");
        verify(menuItemService, times(1)).create(any(), eq(1L));
    }

    @Test
    void testFindById_Success() {
        when(menuItemService.findById(1L)).thenReturn(menuItem);

        ResponseEntity<MenuItem> response = controller.findById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    @Test
    void testFindAll_Success() {
        when(menuItemService.findAll()).thenReturn(List.of(menuItem));

        ResponseEntity<List<MenuItem>> response = controller.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void testFindByRestaurant_Success() {
        when(menuItemService.findByRestaurant(1L)).thenReturn(List.of(menuItem));

        ResponseEntity<List<MenuItem>> response = controller.findByRestaurant(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void testUpdate_Success() {
        when(menuItemService.update(eq(1L), any())).thenReturn(menuItem);

        ResponseEntity<MenuItem> response = controller.update(1L, menuItem);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        verify(menuItemService, times(1)).update(eq(1L), any());
    }

    @Test
    void testDelete_Success() {
        doNothing().when(menuItemService).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(menuItemService, times(1)).delete(1L);
    }
}