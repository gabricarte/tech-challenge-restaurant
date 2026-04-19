package br.com.techchallenge.restaurant.exception;

public class InvalidLoginException extends RuntimeException {
  public InvalidLoginException() {
    super("Credenciais inválidas.");
  }
}