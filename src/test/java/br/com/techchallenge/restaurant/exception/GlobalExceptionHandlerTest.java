package br.com.techchallenge.restaurant.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleOwnerNotFound_Returns404() {
        OwnerNotFoundException ex = new OwnerNotFoundException(1L);
        ResponseEntity<ProblemDetail> response = handler.handleOwnerNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isInstanceOf(Map.class);
    }

    @Test
    void testHandleInvalidLogin_Returns401() {
        InvalidLoginException ex = new InvalidLoginException();
        ResponseEntity<ProblemDetail> response = handler.handleInvalidLogin(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void testHandleOverCapacity_Returns403() {
        RestaurantOverCapacityException ex = new RestaurantOverCapacityException("Capacidade insuficiente");
        ResponseEntity<ProblemDetail> response = handler.handleOverCapacity(ex);

        assertThat(response.getStatusCode().value()).isEqualTo(403);
    }
}

