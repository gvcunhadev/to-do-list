# 📝 To-Do List API

API RESTful desenvolvida com **Java 17** e **Spring Boot** para gerenciamento de tarefas e subtarefas, permitindo criação, listagem, atualização e exclusão, com filtros e validações. 
O banco H2 foi escolhido para simplificar o projeto, mas a arquitetura permite uma migração fluida para qualquer outro banco de dados relacional.

## 📌 Objetivo
Este projeto foi desenvolvido como parte de um projeto pessoal para afiar meus estudos em:
- Java e Spring Boot
- Boas práticas de desenvolvimento
- Banco de dados relacional
- Documentação de API (Swagger/OpenAPI)

---

## 🚀 Funcionalidades
- CRUD Completo: Criar, listar, atualizar e deletar tarefas com todos os seus atributos (título, descrição, data de vencimento, status e prioridade).
- Filtros Dinâmicos: Listagem de tarefas com filtros por status, prioridade e vencimento.
- Validações:
   - Impedir a conclusão de tarefas com subtarefas pendentes.
   - Validação de dados de entrada com @Valid.
- Docker Compose: Gerenciamento do ambiente de desenvolvimento com um simples comando.
- Documentação Interativa: Explore e teste a API através do Swagger.
---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Docker**
- **docker-compose**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **H2 Database** 
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

O console do Banco de Dados estará disponível em:
http://localhost:8080/h2-console

⚙️ Como Executar a Aplicação
---
Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

Docker e Docker Compose

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/gvcunhadev/to-do-list.git
```
## ⚙️ Configure as variáveis de ambiente
Na raíz do seu projeto, crie uma cópia do arquivo .env.example e renomeie para .env.
Abra o arquivo .env e preencha com suas credenciais do banco de dados.

```bash
SPRING_DATASOURCE_URL=jdbc:h2:file:/data/todolistdb
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=sa
```

## ⚙️ Como Executar a Aplicação
Está aplicação está preparada para ser executada com Docker. 

## 🐳 Com Docker
Após clonar o repositório 
```bash
cd seu-repositorio
docker-compose up -d --build
```
Para parar a aplicação
```bash
docker-compose down
```
## 🧪 Testes
(Em desenvolvimento)
Serão implementados testes unitários e de integração utilizando JUnit 5 e Spring Boot Test.

👤 Autor
Gabriela Cunha
