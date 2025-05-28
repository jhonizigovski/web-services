# Projeto Web Services - API RESTful para Gestão de Clientes - Projeto em andamento.

Este projeto é uma API RESTful desenvolvida em **Spring Boot** para gerenciar clientes. Ela permite criar, listar, buscar, atualizar e deletar clientes, além de validar dados e tratar exceções específicas.

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)  
- [Funcionalidades](#funcionalidades)  
- [Modelagem](#modelagem)  
- [Endpoints da API](#endpoints-da-api)  
- [Validações](#validações)  
- [Tratamento de Exceções](#tratamento-de-exceções)  
---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- ModelMapper
- Jakarta Validation (Bean Validation)
- Banco de Dados relacional (ex: H2, MySQL, PostgreSQL)
- Maven ou Gradle (gerenciador de dependências)

## Funcionalidades

- Cadastro de clientes
- Consulta de todos os clientes
- Consulta de cliente por ID
- Atualização dos dados do cliente
- Remoção de cliente
- Contagem de caracteres na razão social com validação de tamanho
- Verificação da presença de substring na razão social
- Validações automáticas via Bean Validation
- Tratamento centralizado de exceções com mensagens personalizadas

## Modelagem

A entidade principal é `Cliente`, com os seguintes atributos:

- `id` (Long): Identificador único gerado automaticamente.
- `razaoSocial` (String): Nome ou razão social da empresa.
- `funcionario` (int): Quantidade de funcionários (mínimo 1).
- `y` (int): Um campo adicional inteiro com valor mínimo 1.

## Endpoints da API

| Método | URL                 | Descrição                                 | Status HTTP          |
|--------|---------------------|-------------------------------------------|---------------------|
| POST   | `/clientes`         | Cadastrar um novo cliente                  | 201 Created         |
| GET    | `/clientes`         | Listar todos os clientes                   | 200 OK              |
| GET    | `/clientes/{id}`    | Buscar cliente por ID                      | 200 OK / 404 Not Found|
| PUT    | `/clientes/{id}`    | Atualizar cliente por ID                   | 200 OK / 404 Not Found|
| DELETE | `/clientes/{id}`    | Remover cliente por ID                     | 200 OK / 404 Not Found|
| GET    | `/clientes/contar/{id}` | Retorna razão social + " LTDA" se tamanho ≤ 10, erro se maior | 200 OK / 400 Bad Request|
| GET    | `/clientes/verificar/{id}` | Verifica se razão social contém "xx"   | 200 OK / 404 Not Found / 400 Bad Request|

## Validações

- `razaoSocial` deve conter ao menos 2 caracteres e não pode ser em branco.
- `funcionario` e `y` devem ser inteiros maiores ou iguais a 1.
- Tamanho da string concatenada no endpoint `/contar/{id}` não pode exceder 10 caracteres (lança exceção).

## Tratamento de Exceções

- Validação de parâmetros com mensagens detalhadas.
- Exceção personalizada `ClienteNotFoundException` para cliente não encontrado.
- Exceções personalizadas `TamanhoExcedido` e `ObjetoNaoEncontrado` para regras específicas do domínio.
- Respostas padronizadas com mensagens claras para o consumidor da API.
