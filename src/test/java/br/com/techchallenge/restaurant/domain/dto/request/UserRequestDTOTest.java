package br.com.techchallenge.restaurant.domain.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDTOTest {

    private final Validator validator;

    UserRequestDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateValidUserRequestDTO() {

        UserRequestDTO dto = new UserRequestDTO(
                "Leticia",
                "leticia@email.com",
                "123456",
                "Rua Teste"
        );

        Set<ConstraintViolation<UserRequestDTO>> violations =
                validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {

        UserRequestDTO dto = new UserRequestDTO(
                "Leticia",
                "email-invalido",
                "123456",
                "Rua Teste"
        );

        Set<ConstraintViolation<UserRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}