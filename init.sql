CREATE DATABASE IF NOT EXISTS db_restaurant;
USE db_restaurant;

CREATE TABLE IF NOT EXISTS tb_user_type (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_owner (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    last_update_date DATE NOT NULL,
    last_update DATETIME(6),
    user_type_id BIGINT,
    CONSTRAINT FK_owner_user_type FOREIGN KEY (user_type_id) REFERENCES tb_user_type(id)
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
    telephone VARCHAR(255),
    user_type_id BIGINT,
    CONSTRAINT FK_customer_user_type FOREIGN KEY (user_type_id) REFERENCES tb_user_type(id)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_restaurant (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             name VARCHAR(255),
    address VARCHAR(255),
    cuisine_type VARCHAR(255),
    capacity INT,
    opening_time TIME,
    closing_time TIME,
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

INSERT INTO tb_user_type (name) VALUES ('Dono de Restaurante');
INSERT INTO tb_user_type (name) VALUES ('Cliente');

INSERT INTO tb_customer (name,email,login,password,address,last_update_date,last_update,birth_date,cpf,telephone,user_type_id)
VALUES ('Ana Souza','ana.souza@email.com','ana_cliente','Cliente#123','Rua das Flores, 123','2026-05-02','2026-05-02 15:27:59','1995-03-15','12345678901','11988887777',2);

INSERT INTO tb_owner (name,email,login,password,address,last_update_date,last_update,user_type_id)
VALUES
    ('Ricardo Almeida','ricardo.proprietario@restaurante.com.br','ricardo_owner','StrongPassword!2026','Av. Paulista, 1500','2026-05-02','2026-05-02 15:27:59',1),
    ('Fernanda Oliveira','fernanda.oliveira@restaurante.com.br','fernanda_owner','SecurePassword!2026','Rua Oscar Freire, 1100, Jardins, São Paulo - SP','2026-05-02','2026-05-02 15:27:59',1);

INSERT INTO tb_restaurant (name, address, cuisine_type, capacity, opening_time, closing_time, owner_id)
VALUES
    ('Sabor Brasileiro', 'Rua da Consolação, 1200', 'Brasileira', 50, '11:30:00', '23:00:00', 1),
    ('Sushi Zen', 'Av. Paulista, 800', 'Japonesa', 30, '19:00:00', '23:30:00', 2);