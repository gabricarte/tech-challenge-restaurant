package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    @Test
    void testLogin() {

        Map<String, String> body = Map.of(
                "login", "admin",
                "password", "123"
        );

        ResponseEntity<Void> response = controller.login(body);

        assertEquals(200, response.getStatusCode().value());

        verify(userService).validarLogin("admin", "123");
    }
}