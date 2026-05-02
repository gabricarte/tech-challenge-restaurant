package br.com.techchallenge.restaurant.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OwnerRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "O login é obrigatório")
        @Size(min = 4, max = 20, message = "O login deve ter entre 4 e 20 caracteres")
        String login,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String password,

        @NotBlank(message = "O endereço é obrigatório")
        String address
) {}