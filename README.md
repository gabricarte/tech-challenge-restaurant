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

Detalhamento dos Endpoints (Postman)

### 📬 Endpoints da API

Endpoints da API
👤 Owner
Cadastrar Proprietário
POST /api/v1/owners

JSON
{
  "name": "Leticia Gabriela",
  "email": "leticia@teste.com",
  "login": "leticiagabriela",
  "password": "pompompurin123",
  "address": "Rua do Tech Challenge, 123"
}
👥 Customer
Registrar Cliente
POST /api/v1/customers

JSON
{
  "name": "Galera fiap",
  "email": "fiap@test.com",
  "login": "galera_fiap",
  "password": "senhaSegura123",
  "birthDate": "1998-10-15",
  "address": "Rua da Fiap, 123",
  "cpf": "12345678900",
  "telefone": "11999999999"
}
👤 User
Buscar usuário por ID
GET /api/v1/users/{id}

Atualizar usuário
PUT /api/v1/users/{id}

Remover usuário
DELETE /api/v1/users/{id}

Alterar senha
PATCH /api/v1/users/{id}/password

JSON
{
  "newPassword": "pompompurin123"
}
🔐 Auth
Autenticação (Login)
POST /api/v1/users/login

🍽️ Restaurant
Listar Restaurantes
GET /api/v1/restaurants/{id}

JSON
{
  "name": "Pizzaria legabi - Edição Especial",
  "address": "Rua das Flores, 123",
  "cuisineType": "Italiana",
  "capacity": 50,
  "ownerName": "Leticia Gabriela"
}
📦 Booking
Criar novo pedido
POST /api/v1/bookings

JSON
{
  "customerName": "Leticia",
  "numberOfPeople": 40,
  "dateTime": "2026-04-20T20:00:00"
}

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


