# Monitora Consumo

## O que é este projeto?

Este é um projeto do Tech Challenge da Postech/FIAP de Arquitetura de Desenvolvimento Java.
Ele é um sistema Web que tem como finalidade calcular o consumo
mensal de energia, o qual deverá permitir o cadastro de Pessoas, Casas e Eletrodomésticos,
com interfaces e APIs.

## O que eu devo configurar?

Este projeto implementa uma API Rest com Spring Boot.
Na máquina é preciso ter instalado: 
 * java versão 17
 * maven

## Como executar este projeto?

Este projeto foi construído com Spring Boot e pode ser executado através do comando:
```bash
mvn spring-boot:run
```

## Como testar o projeto?

As urls dos endpoints disponíveis para acesso são as abaixo:

### Enderecos 
* http://localhost:8080/enderecos

As operações possíveis são:
- `GET` - listar todos os endereços: http://localhost:8080/enderecos
- `GET` - buscar por id: http://localhost:8080/enderecos/{id}
- `POST` - cadastrar: http://localhost:8080/enderecos
- `PUT` - atualizar: http://localhost:8080/enderecos/{id}
- `DELETE` - remover: http://localhost:8080/enderecos/{id}

Exemplo de chamada do verbo `POST`:

```json
{
    "rua":  "Rua 1",
    "numero": 1,
    "bairro": "Bairro 1",
    "cidade": "Cidade 1",
    "estado":  "Estado 1"
}
```
Um exemplo de retorno será:
```json
{
"id": "c4f38178-df26-41eb-9a25-be8e198750cd",
"rua": "Rua 1",
"numero": 1,
"bairro": "Bairro 1",
"cidade": "Cidade 1",
"estado": "Estado 1"
}
```

### Pessoas

* http://localhost:8080/pessoas

As operações possíveis são:
- `GET` - listar todos: http://localhost:8080/pessoas
- `GET` - buscar por id: http://localhost:8080/pessoas/{id}
- `POST` - cadastrar: http://localhost:8080/pessoas
- `PUT` - atualizar: http://localhost:8080/pessoas/{id}
- `DELETE` - remover: http://localhost:8080/pessoas/{id}

Exemplo de chamada do verbo `POST`:

```json
{
  "nome":  "João",
  "dataDeNascimento": "2000-05-10",
  "sexo": "MASCULINO",
  "parentesco": "IRMAO"
}
```
Um exemplo de retorno será:
```json
{
  "id": "3af8ee15-cec0-45c5-b3e3-3197bbf6898e",
  "nome": "João",
  "dataDeNascimento": "2000-05-10",
  "sexo": "MASCULINO",
  "parentesco": "IRMAO"
}
```

### Eletrodomésticos

* http://localhost:8080/eletrodomesticos

As operações possíveis são:
- `GET` - listar todos: http://localhost:8080/eletrodomesticos
- `GET` - buscar por id: http://localhost:8080/eletrodomesticos/{id}
- `POST` - cadastrar: http://localhost:8080/eletrodomesticos
- `PUT` - atualizar: http://localhost:8080/eletrodomesticos/{id}
- `DELETE` - remover: http://localhost:8080/eletrodomesticos/{id}

Exemplo de chamada do verbo `POST`:

```json
{
  "nome":  "TV",
  "modelo": "Panasonic",
  "potencia": 700.00
}
```
Um exemplo de retorno será:
```json
{
  "id": "f08ba83c-86f4-40eb-8a74-a57725f30fae",
  "nome": "TV",
  "modelo": "Panasonic",
  "potencia": 700.00
}
```

## Tecnologias e ferramentas utilizadas

Para o projeto foi utilizado:

* Spring Boot para facilitar a criação e start do projeto;
* Spring Rest API para os endpoints;
* Spring Data com H2 (banco em memória) para persistência de dados;
* Bean validation para validação dos dados de entrada;
* Biblioteca Lombok para reduzir código boilerplate de algumas classes;
* Programa Postman para executar as requisições de teste para os endpoints.

## Desafios encontrados

Os principais desafios encontrados foram os seguintes:

* Definição de como mapear as classes de entrada (DTOs) para as classes de modelo: Usei um mapeamento direto,
sem a utilização de nenhum mapper, dada a quantidade pequena de atributos das APIs;
* Manter os repositórios em uma Collection em memória ou usar um banco com Spring Data: Optei pelo Spring
Data pela facilidade de mapeamento das tabelas e da configuração do Spring Data, com um banco de dados
em memória (H2);
* Realizar validação em todos os atributos de entrada ou apenas em alguns deles: Optei por realizar a validação
em todos também dada a facilidade da inclusão das validações com Bean Validation, permitindo maior
controle do que é enviado para as APIs.