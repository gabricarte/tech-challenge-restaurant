package br.com.techchallenge.restaurant.service;

import br.com.techchallenge.restaurant.domain.entity.MenuItem;
import br.com.techchallenge.restaurant.repository.MenuItemRepository;
import br.com.techchallenge.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    @Test
    void testFindById() {

        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Pizza");

        when(menuItemRepository.findById(1L))
                .thenReturn(Optional.of(menuItem));

        MenuItem result = menuItemService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Pizza", result.getName());
    }
}