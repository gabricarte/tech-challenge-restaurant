# 🍴 Tech Challenge - Restaurant API (FIAP)

A **Restaurant API** é uma solução robusta desenvolvida com Spring Boot para a gestão completa de operações de autoatendimento em restaurantes. Este projeto faz parte do entregável do Tech Challenge da pós-graduação em Software Architecture da FIAP.

## 📋 Visão Geral
A API foi desenhada para suportar o fluxo principal de um restaurante, incluindo:
* **Identificação:** Cadastro e gestão de clientes (Customers).
* **Segurança:** Autenticação e gestão de usuários.
* **Operacional:** Listagem de restaurantes e criação de pedidos (Bookings).

## 🏗️ Arquitetura e Design
O projeto foi estruturado utilizando princípios de **Clean Architecture** e **DDD (Domain-Driven Design)** para garantir testabilidade e baixo acoplamento.

**Estrutura de Pastas:**
- `domain`: Núcleo da aplicação (Entidades e Regras de Negócio).
- `application`: Casos de uso e DTOs.
- `infrastructure`: Configurações de banco de dados, persistência e integrações externas.
- `interfaces`: Controllers e mapeamento das rotas da API.

---

## 🛠️ Tecnologias Utilizadas
- **Java 21** + **Spring Boot 3.x**
- **Spring Data JPA** (Persistência)
- **PostgreSQL** (Banco de dados relacional)
- **Docker & Docker Compose** (Containerização)
- **Swagger (OpenAPI 3)** (Documentação)
- **Postman** (Suíte de testes)

---

✅ Boas Práticas Aplicadas
Separação de responsabilidades por camada
Arquitetura escalável e de fácil manutenção
Uso de variáveis externas (Docker / env)
Organização orientada a domínio (DDD)
Preparação para autenticação robusta (JWT)
🧪 Testes da API

A API foi validada utilizando o Postman, garantindo:

Funcionamento dos endpoints
Validação de autenticação
Consistência das operações CRUD

---

Endpoints da API

👤 Owner
GET /restaurant/owner

👥 Customer
POST /customers — Registrar cliente

👤 User
GET /users/{id} — Buscar usuário por ID

PUT /users/{id} — Atualizar usuário

DELETE /users/{id} — Remover usuário

PATCH /users/{id}/password — Alterar senha

🔐 Auth
POST /auth/login — Autenticação

🍽️ Restaurant
GET /restaurants — Listar restaurantes

📦 Booking
POST /bookings — Criar novo pedido

---

Documentação da API
Swagger UI:
👉 http://localhost:8080/swagger-ui.html
OpenAPI Docs:
👉 http://localhost:8080/api-docs

---

## 🐳 Execução via Docker
Para subir o ambiente completo (API + Banco de Dados), utilize o comando abaixo na raiz do projeto:

```bash
docker-compose up --build -d


