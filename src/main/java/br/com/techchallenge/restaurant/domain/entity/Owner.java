package br.com.techchallenge.restaurant.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "tb_owner")
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends User {
}