package br.com.techchallenge.restaurant.exception;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(Long id) {
        super("Menu item não encontrado. Id: " + id);
    }
}