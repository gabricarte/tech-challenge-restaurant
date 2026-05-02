package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByLogin(String login);

    List<Owner> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);
}