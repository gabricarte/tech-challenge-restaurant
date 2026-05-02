package br.com.techchallenge.restaurant.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("O E-mail " + email + " já possui cadastro. ");
    }
}
