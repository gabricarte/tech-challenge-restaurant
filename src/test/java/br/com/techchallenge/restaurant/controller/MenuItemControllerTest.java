package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController controller;

    @Test
    void testCreate() {
        MenuItem item = new MenuItem();
        item.setId(1L);
        item.setName("Pizza");
        item.setDescription("Pizza Calabresa");

        when(menuItemService.create(any(MenuItem.class), eq(1L)))
                .thenReturn(item);

        ResponseEntity<MenuItem> response = controller.create(item, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pizza", response.getBody().getName());

        verify(menuItemService, times(1)).create(item, 1L);
    }
}