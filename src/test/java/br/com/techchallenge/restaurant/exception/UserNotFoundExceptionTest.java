package br.com.techchallenge.restaurant.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

 class UserNotFoundExceptionTest {

        @Test
        void testConstructor() {

            UserNotFoundException exception = new UserNotFoundException("Usuário não encontrado.");

            assertThat(exception.getMessage())
                    .isEqualTo("Usuário não encontrado.");
        }
    }