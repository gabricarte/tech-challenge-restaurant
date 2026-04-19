package br.com.techchallenge.restaurant.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
      super("Cliente com ID " + id + " não localizado na base de dados.");
    }
}
