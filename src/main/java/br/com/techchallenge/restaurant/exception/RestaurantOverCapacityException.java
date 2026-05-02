package br.com.techchallenge.restaurant.exception;

public class RestaurantOverCapacityException extends RuntimeException {
    public RestaurantOverCapacityException(String message) {
        super(message);
    }
}
