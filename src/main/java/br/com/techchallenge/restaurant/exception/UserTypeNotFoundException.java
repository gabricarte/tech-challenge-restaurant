package br.com.techchallenge.restaurant.exception;

public class UserTypeNotFoundException extends RuntimeException {
    public UserTypeNotFoundException(Long id) {
        super("Tipo de usuário com id:  " + id + " não encontrado.");
    }
}
