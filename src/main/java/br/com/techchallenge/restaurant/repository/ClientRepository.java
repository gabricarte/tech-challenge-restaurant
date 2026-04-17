package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
