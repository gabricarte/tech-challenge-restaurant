package br.com.techchallenge.restaurant.repository;

import br.com.techchallenge.restaurant.domain.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
