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
- `GET` - buscar por rua, bairro ou cidade: http://localhost:8080/enderecos/buscar
- `POST` - cadastrar: http://localhost:8080/enderecos
- `POST` - adicionar pessoa ao endereço: http://localhost:8080/enderecos/{idEndereco}/adicionarPessoa/{idPessoa}
- `POST` - adicionar eletrodoméstico ao endereço: http://localhost:8080/enderecos/{idEndereco}/adicionarEletrodomestico/{idEletrodomestico}
- `PUT` - atualizar: http://localhost:8080/enderecos/{id}
- `DELETE` - remover: http://localhost:8080/enderecos/{id}

Exemplo de chamada do verbo `POST` para cadastrar endereço:

```json
{
    "rua":  "Rua 1",
    "numero": 1,
    "bairro": "Bairro 1",
    "cidade": "Cidade 1",
    "estado":  "Estado 1",
    "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
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
    "estado": "Estado 1",
    "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
}
```

### Pessoas

* http://localhost:8080/pessoas

As operações possíveis são:
- `GET` - listar todos: http://localhost:8080/pessoas
- `GET` - buscar por id: http://localhost:8080/pessoas/{id}
- `GET` - buscar por nome, parentesco ou sexo: http://localhost:8080/pessoas/buscar
- `GET` - verificar parentesco: http://localhost:8080/pessoas/{id}/verificaParentesco
- `POST` - cadastrar: http://localhost:8080/pessoas
- `PUT` - atualizar: http://localhost:8080/pessoas/{id}
- `DELETE` - remover: http://localhost:8080/pessoas/{id}

Exemplo de chamada do verbo `POST` para cadastrar endereço:

```json
{
  "nome":  "João",
  "dataDeNascimento": "2000-05-10",
  "sexo": "MASCULINO",
  "parentesco": "IRMAO",
  "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
}
```
Um exemplo de retorno será:
```json
{
  "id": "3af8ee15-cec0-45c5-b3e3-3197bbf6898e",
  "nome": "João",
  "dataDeNascimento": "2000-05-10",
  "sexo": "MASCULINO",
  "parentesco": "IRMAO",
  "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
}
```

### Eletrodomésticos

* http://localhost:8080/eletrodomesticos

As operações possíveis são:
- `GET` - listar todos: http://localhost:8080/eletrodomesticos
- `GET` - buscar por id: http://localhost:8080/eletrodomesticos/{id}
- `GET` - buscar por nome, modelo ou potência: http://localhost:8080/eletrodomesticos/buscar
- `POST` - cadastrar: http://localhost:8080/eletrodomesticos
- `PUT` - atualizar: http://localhost:8080/eletrodomesticos/{id}
- `DELETE` - remover: http://localhost:8080/eletrodomesticos/{id}

Exemplo de chamada do verbo `POST` para cadastar eletrodoméstico:

```json
{
  "nome":  "TV",
  "modelo": "Panasonic",
  "potencia": 700.00,
  "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
}
```
Um exemplo de retorno será:
```json
{
  "id": "f08ba83c-86f4-40eb-8a74-a57725f30fae",
  "nome": "TV",
  "modelo": "Panasonic",
  "potencia": 700.00,
  "idUsuario": "adb61251-4fa2-4c8c-841e-00baecf607b9"
}
```

### Consumos

* http://localhost:8080/consumos

As operações possíveis são:
- `POST` - registrar consumo de eletrodoméstico por uma pessoa, num intervalo de tempo: http://localhost:8080/consumos

Exemplo de chamada do verbo `POST` para registrar o consumo:

```json
{
  "idPessoa": "0bd236c4-7e9d-4814-8410-92651e086e75",
  "idEletrodomestico": "34162434-270a-4090-b3ce-47245f898a86",
  "inicioConsumo": "2023-05-06T01:10:10",
  "terminoConsumo": "2023-05-06T01:10:20"
}
```
Um exemplo de retorno será:
```json
{
  "id": "4d6d6c75-7b85-481e-94c3-581d5fa48b78",
  "idPessoa": "0bd236c4-7e9d-4814-8410-92651e086e75",
  "idEletrodomestico": "34162434-270a-4090-b3ce-47245f898a86",
  "inicioConsumo": "2023-05-06T01:10:10",
  "terminoConsumo": "2023-05-06T01:10:20"
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

## Fase 1 - Desafios encontrados

* Definição de como mapear as classes de entrada (DTOs) para as classes de modelo: Usei um mapeamento direto,
sem a utilização de nenhum mapper, dada a quantidade pequena de atributos das APIs;
* Manter os repositórios em uma Collection em memória ou usar um banco com Spring Data: Optei pelo Spring
Data pela facilidade de mapeamento das tabelas e da configuração do Spring Data, com um banco de dados
em memória (H2);
* Realizar validação em todos os atributos de entrada ou apenas em alguns deles: Optei por realizar a validação
em todos também dada a facilidade da inclusão das validações com Bean Validation, permitindo maior
controle do que é enviado para as APIs.

## Fase 2 - Desafios encontrados 

- Definição se a busca deveria ser implementada para retornar uma única entidade ou se retornaria uma ou mais,
e se os parâmetros do endpoint seriam obrigatórios ou não. Concluímos definindo que a busca
poderia retornar mais de uma entidade, com base no critério de 'OU' e os parâmetros sendo
opcionais;
- A forma de implementação dos relacionamentos: 
  - Definimos que as 3 entidades principais do domínio do negócio (Eletrodoméstico, Endereço e Pessoa)
teriam um atributo indicando o Usuário que realizou o cadastro;
  - O Endereço teria tanto uma lista de Pessoas como uma lista de Eletrodomésticos;
  - A relação entre as Pessoas e os Eletrodomésticos seriam representados pela entidade Consumo,
que teve sua própria API criada.