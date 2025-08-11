# 📝 To-Do List API

API RESTful desenvolvida com **Java 17** e **Spring Boot** para gerenciamento de tarefas e subtarefas, permitindo criação, listagem, atualização e exclusão, com filtros e validações.

## 📌 Objetivo
Este projeto foi desenvolvido como parte de um projeto pessoal para afiar meus estudos em:
- Java e Spring Boot
- Boas práticas de desenvolvimento
- Banco de dados relacional
- Documentação de API (Swagger/OpenAPI)

---

## 🚀 Funcionalidades

- Criar tarefa com título, descrição, data de vencimento, status e prioridade
- Listar tarefas com filtros por status, prioridade e vencimento
- Atualizar o status da tarefa
- Deletar tarefa
- **Validação:** Impedir conclusão de tarefa com subtarefas pendentes
- Documentação interativa da API com Swagger

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **H2 Database** (padrão, para testes locais)
- **Lombok**
- **SpringDoc OpenAPI** (Swagger)
- **Maven**

---

```
## 📂 Estrutura do Projeto

src/main/java/com/todolist
│── controller/ # Controllers REST
│── dto/ # Objetos de Transferência de Dados (DTOs)
│── exception/ # Tratamento de exceções
│── model/ # Entidades e enums
│── repository/ # Interfaces de persistência (JPA)
│── service/ # Regras de negócio
└── TodolistApplication.java
```

## 📄 Documentação da API

Após rodar o projeto, a documentação estará disponível em:
http://localhost:8080/swagger-ui/index.html

## ⚙️ Como Rodar Localmente

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
mvn spring-boot:run
```
🧪 Testes
(Em desenvolvimento)
Serão implementados testes unitários e de integração utilizando JUnit 5 e Spring Boot Test.

🐳 Docker
(Em desenvolvimento)
Será adicionado Docker Compose para subir a aplicação junto com o banco de dados (PostgreSQL ou MySQL).

👤 Autor
Gabriela Cunha
