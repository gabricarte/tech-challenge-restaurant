package br.com.techchallenge.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {
  public RestaurantNotFoundException(Long id) {
    super("Restaurante com ID " + id + " não encontrado.");
  }
}