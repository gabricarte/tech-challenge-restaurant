package br.com.techchallenge.restaurant.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
      super("Cliente com ID " + id + " não localizado na base de dados.");
    }
}
