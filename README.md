# api-rest-java-spring

API REST para cadastro de clientes com consulta de endereço por CEP, desenvolvida em Java com Spring Boot.

## Visão geral

Este projeto expõe endpoints para:

- cadastrar, consultar, atualizar e excluir clientes;
- consultar endereços por CEP usando a API pública do ViaCEP;
- persistir clientes e endereços em banco relacional com JPA;
- documentar a API via Swagger/OpenAPI.

## Stack tecnológica

- Java 25
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA
- Spring Cloud OpenFeign
- H2 Database
- Springdoc OpenAPI
- Maven

## Estrutura do projeto

```text
webapp/
├── src/
│   ├── main/
│   │   ├── java/com/example/webapp/webapp/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── WebappApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/webapp/webapp/
├── .mvn/
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Requisitos

- JDK 25
- Maven (ou use o wrapper do projeto `./mvnw`)
- Internet para consultar o ViaCEP

## Como executar

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

A aplicação inicia por padrão em:

```text
http://localhost:8080
```

## Documentação da API

Com a aplicação em execução, a documentação OpenAPI/Swagger fica disponível em:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints

### Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/cliente` | Lista todos os clientes |
| POST | `/cliente` | Cria um cliente |
| GET | `/cliente/{id}` | Busca cliente por ID |
| PATCH | `/cliente/{id}` | Atualiza parcialmente um cliente |
| PUT | `/cliente/{id}` | Atualiza totalmente um cliente |
| DELETE | `/cliente/{id}` | Remove um cliente |

Exemplo de criação de cliente:

```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "senha": "senha1234",
  "enderecos": ["01001000", "01310970"]
}
```

Exemplo de resposta:

```json
{
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "enderecos": [
    {
      "cep": "01001000",
      "logradouro": "Praça da Sé",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP",
      "estado": "São Paulo",
      "regiao": "Sudeste"
    }
  ]
}
```

### Endereços

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/endereco` | Lista todos os endereços cadastrados |
| POST | `/endereco/{cep}` | Busca e salva um endereço pelo CEP |
| GET | `/endereco/{cep}` | Busca endereço por CEP |

Exemplo de consulta:

```bash
curl http://localhost:8080/endereco/01001000
```

## Modelo de domínio

### Cliente

Campos principais:

- `id`
- `nome`
- `email`
- `senha`
- `enderecos`

### Endereco

Campos principais:

- `cep`
- `logradouro`
- `complemento`
- `bairro`
- `localidade`
- `uf`
- `estado`
- `regiao`

## Observações

- O sistema consulta automaticamente os dados do endereço no ViaCEP quando não estiverem armazenados localmente.
- O banco usado por padrão é o H2 em memória, configurado pela Spring Boot.
- As validações de entrada são feitas com Bean Validation (`jakarta.validation`), incluindo regras para nome, e-mail, senha e CEP.

## Testes

Para executar os testes:

```bash
./mvnw test
```

## Licença

Este projeto foi desenvolvido como exemplo acadêmico/protótipo de API REST em Spring Boot.
