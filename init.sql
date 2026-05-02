CREATE DATABASE IF NOT EXISTS db_restaurant;
USE db_restaurant;

CREATE TABLE IF NOT EXISTS tb_owner (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    last_update_date DATE NOT NULL,
    last_update DATETIME(6)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_customer (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    last_update_date DATE NOT NULL,
    last_update DATETIME(6),
    birth_date DATE,
    cpf VARCHAR(255),
    telephone VARCHAR(255)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_restaurant (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             name VARCHAR(255),
    address VARCHAR(255),
    cuisine_type VARCHAR(255),
    capacity INT,
    owner_id BIGINT,
    CONSTRAINT FK_restaurant_owner FOREIGN KEY (owner_id) REFERENCES tb_owner(id)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_booking (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          customer_name VARCHAR(255),
    date_time DATETIME(6),
    number_of_people INT,
    status VARCHAR(255),
    restaurant_id BIGINT,
    CONSTRAINT FK_booking_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurant(id)
    ) ENGINE=InnoDB;


INSERT INTO tb_owner (name, email, login, password, address, last_update_date, last_update)
VALUES ('Ricardo Almeida', 'ricardo.proprietario@restaurante.com.br', 'ricardo_owner', 'StrongPassword!2026', 'Av. Paulista, 1500', '2026-05-02', NOW());

INSERT INTO tb_owner (name, email, login, password, address, last_update_date, last_update)
VALUES ('Fernanda Oliveira', 'fernanda.oliveira@restaurante.com.br', 'fernanda_owner', 'SecurePassword!2026', 'Rua Oscar Freire, 1100, Jardins, São Paulo - SP', '2026-05-02', NOW());

INSERT INTO tb_customer (name, email, login, password, address, last_update_date, last_update, birth_date, cpf, telephone)
VALUES ('Ana Souza', 'ana.souza@email.com', 'ana_cliente', 'Cliente#123', 'Rua das Flores, 123', '2026-05-02', NOW(), '1995-03-15', '12345678901', '11988887777');

INSERT INTO tb_restaurant (name, address, cuisine_type, capacity, owner_id)
VALUES ('Cantinho Paulista', 'Av. Paulista, 1502', 'Francesa', 40, 1);

INSERT INTO tb_booking (customer_name, date_time, number_of_people, status, restaurant_id)
VALUES ('Ana Souza', '2026-05-10 20:00:00', 2, 'CONFIRMED', 1);