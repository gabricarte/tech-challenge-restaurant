package br.com.techchallenge.restaurant.domain.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    OWNER(1L, "Dono de Restaurante"),
    CUSTOMER(2L, "Cliente");

    private final Long id;
    private final String name;

    UserTypeEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}