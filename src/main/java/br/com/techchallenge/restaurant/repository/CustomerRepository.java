package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByLogin(String login);

    boolean existsByEmail(String email);

    List<Customer> findByNameContainingIgnoreCase(String name);
}