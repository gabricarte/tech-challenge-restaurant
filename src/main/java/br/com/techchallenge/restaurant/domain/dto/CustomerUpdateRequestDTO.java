package br.com.techchallenge.restaurant.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CustomerUpdateRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O login é obrigatório")
        @Size(min = 4, max = 20, message = "O login deve ter entre 4 e 20 caracteres")
        String login,

        @NotBlank(message = "O telefone é obrigatório")
        String telephone,

        @NotBlank(message = "O endereço é obrigatório")
        String address,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data no passado")
        LocalDate birthDate
) {}
