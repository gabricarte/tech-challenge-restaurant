package br.com.techchallenge.restaurant.exception;

public class LoginInvalidoException extends RuntimeException {
  public LoginInvalidoException() {
    super("Credenciais inválidas.");
  }
}