# Sistema de Triagem - Pronto Socorro

Este projeto simula um sistema backend de triagem de pacientes em um pronto socorro. Ele expõe uma API RESTful para cadastro e atendimento de pacientes, médicos e controle de plantão.

---

## Tecnologias Utilizadas

- Java 17
- Maven
- Spring Boot
- Spring Data JPA
- Banco de dados em memória (H2)
- Comunicação via JSON

---

## Requisitos para execução

- Java 17 ou superior instalado
- Maven instalado
- Editor com suporte a REST Client (recomendado: VS Code com extensão **REST Client**)

---

## Como executar o projeto

```bash
git clone https://github.com/seu-usuario/triagem-pronto-socorro.git
cdtriagem-pronto-socorro
mvn spring-boot:run
```

A aplicação estará acessível em:  
 `http://localhost:8080/health`

---

##  Testar a API com arquivo `.http`

Este projeto já inclui um arquivo chamado `requests.http`, com todas as requisições prontas.

### Como usar no VS Code:

1. Instale a extensão `REST Client`
2. Abra o arquivo `requests.http`
3. Clique em “Send Request” em qualquer requisição

---

##  Endpoints disponíveis

- `GET /health` — Verifica se o sistema está no ar
- `POST /triagem` — Cadastra novo paciente
- `POST /medicos` — Cadastra novo médico
- `PUT /medicos/{id}/plantao?emPlantao=true|false` — Altera status de plantão
- `GET /pacientes/{id}` — Busca paciente por ID
- `GET /atendimento/proximo` — Retorna o próximo paciente a ser atendido

---

##  Banco de Dados

- O sistema usa **H2 (em memória)** para facilitar testes.
- Console do banco acessível em: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:triagemdb`
  - Usuário: `sa`
  - Senha: *(deixe em branco)*

---

## Observação

Este projeto foi desenvolvido com foco em princípios de orientação a objetos e arquitetura em camadas (Model, Repository, Service, Controller). O arquivo `requests.http` facilita o teste completo da aplicação sem necessidade de ferramentas externas.
