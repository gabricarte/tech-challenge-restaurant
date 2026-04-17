package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
