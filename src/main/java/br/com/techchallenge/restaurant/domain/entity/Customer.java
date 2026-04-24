package br.com.techchallenge.restaurant.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String cpf;
    private String telephone;
}
