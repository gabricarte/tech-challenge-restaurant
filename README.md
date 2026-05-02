# 🍴 Tech Challenge - Restaurant API (FIAP)

A **Restaurant API** é uma solução desenvolvida com Spring Boot para a gestão de restaurantes. Este projeto faz parte do entregável do Tech Challenge da pós-graduação em Software Architecture da FIAP.

## 📋 Visão Geral
A API suporta o fluxo operacional completo, desde o cadastro administrativo até a reserva de mesas:
*   **Gestão de Usuários:** Identificação distinta entre Proprietários (`Owners`) e Clientes (`Customers`).
*   **Marketing e CRM:** Captura de dados estratégicos (Aniversário, CPF, Telefone) para engajamento e fidelização.
*   **Operacional:** Cadastro de estabelecimentos e gestão de reservas (`Bookings`).

## 🏗️ Arquitetura e Design
O projeto foi estruturado seguindo o padrão de **Arquitetura em Camadas (Layered Architecture)**, garantindo a separação de responsabilidades e facilitando a manutenção:

*   **Controller Layer**: Exposição de endpoints REST, validação de entrada (`Bean Validation`) e mapeamento de DTOs.
*   **Service Layer**: Concentração de toda a lógica de negócio e regras de validação.
*   **Domain Layer**: Definição de entidades e utilização de `@MappedSuperclass` para herança entre perfis de usuário.
*   **Repository Layer**: Abstração da persistência de dados via Spring Data JPA.
*   **Infrastructure & Configuration**: Tratamento global de exceções e configurações de infraestrutura.

---

## 🛠️ Tecnologias Utilizadas
- **Java 17** + **Spring Boot 3.x**
- **Spring Data JPA** + **Hibernate** (ORM)
- **MySQL 8.x** (Banco de dados relacional)
- **Docker & Docker Compose** (Containerização)
- **Swagger (OpenAPI 3)** (Documentação interativa)
- **Lombok** (Redução de Boilerplate)

---

## ✅ Diferenciais Técnicos e Boas Práticas
*   **RFC 7807 (Problem Detail)**: Padronização de respostas de erro para facilitar o consumo da API por front-ends e integradores.
*   **Segurança de Dados**: Endpoints distintos para atualização de perfil e troca de senha, seguindo o princípio de menor privilégio.
*   **Versionamento**: API versionada via URL (`/api/v1/`).
*   **Imutabilidade**: Uso de `Records` para DTOs, garantindo a integridade dos dados durante o trânsito entre camadas.

---

## 🐳 Execução via Docker

A aplicação utiliza um **Dockerfile multi-stage**, o que automatiza integralmente o ciclo de build e execução. Este processo garante que o artefato `.jar` seja gerado em um ambiente isolado e padronizado, eliminando a necessidade de configurações prévias de Java ou Maven no host local.

**Para subir o ambiente completo (API + Banco de Dados MySQL):**
```bash
docker-compose up --build
