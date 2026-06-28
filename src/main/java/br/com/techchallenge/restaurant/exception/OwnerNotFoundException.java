package br.com.techchallenge.restaurant.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Long id) {
        super("Proprietário com ID " + id + " não encontrado. Verifique as credenciais.");
    }
}
