# Backend da TokioMarine Seguradora

API para o funcionamento do front-end de cadastro de clientes e consulta de dados de endereço a partir do CEP.

## Requisitos do Sistema

- Java 11
- Spring Boot 2.1.5
- Maven 3.9.5
- H2 (Banco de Dados em Memória)

## Instalação

1. Clone o repositório para sua máquina local.
    ```bash
    git clone https://github.com/lucasCoelho245/tokio-user-interface-backend.git
    ```

2. Navegue até o diretório do projeto.
    ```bash
    cd tokio-user-interface-backend
    ```

3. Compile o projeto e baixe as dependências.
    ```bash
    mvn clean install
    ```

4. Inicie a aplicação.
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

## Uso

A API permite o cadastro de clientes e a consulta de endereços com base no CEP. A URL base para interagir com a API é `http://localhost:8080/clients`.

### Endpoints Principais

#### Cadastro de Cliente
- **`POST /clients`**: Cadastra um novo cliente, incluindo informações de nome, CPF, email e endereço (CEP).
    - Body:
      ```json
      {
        "name": "João Silva",
        "cpf": "123.456.789-00",
        "email": "joao@example.com",
        "phone": "(11) 99999-9999",
        "addresses": [
          {
            "street": "Rua Exemplo",
            "city": "São Paulo",
            "state": "SP",
            "complement": "",
            "district": "Centro",
            "zipcode": "01235-000"
          }
        ]
      }
      ```

#### Consulta de Clientes
- **`GET /clients`**: Retorna todos os clientes cadastrados, incluindo os dados de endereço associados a cada cliente.
    - Exemplo de resposta:
      ```json
      [
        {
          "id": 1,
          "name": "Usuário Teste",
          "cpf": "123.456.789-00",
          "email": "teste@example.com",
          "phone": "(11) 99999-9999",
          "addresses": [
            {
              "id": 1,
              "street": "Rua Traipu",
              "city": "São Paulo",
              "state": "SP",
              "complement": "",
              "district": "Pacaembu",
              "zipcode": "01235-000"
            }
          ]
        }
      ]
      ```

#### Persistência de Dados
Foi optado por usar o banco de dados H2 para persistência de dados em memória. Para limpar os dados do banco, basta reiniciar a aplicação.


#### Consumindo um Serviço Externo de CEP

#### A  API também consome o seguinte serviço para obter o endereço dado o CEP:

- **URL**: `https://viacep.com.br/ws/{cep}/json/`
  Exemplo de requisição:
```sh
# Request
$ curl https://viacep.com.br/ws/01001000/json/

# Response
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "unidade": "",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "estado": "São Paulo",
  "regiao": "Sudeste",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107"
}


