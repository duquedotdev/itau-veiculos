﻿<center> 
  <h1 align="center">TESTE TÉCNICO - ITAÚ</h1>
  <p align="center">
    Microserviço de cadastro de usuários para o teste técnico do Itaú Veículos <br />
    Utilizando Clean Architecture, DDD, TDD e as boas práticas atuais de mercado
  </p>
</center>
<br />

## Candidato

- Felipe Duque
- 29 anos
- TCS


### Links Úteis

* Quando Instanciada de Forma local ela fica acessível em: http://localhost:8080/swagger-ui/index.html <br />

* A API está disponível agora mesmo em: http://ec2-18-224-23-118.us-east-2.compute.amazonaws.com:8080/swagger-ui/index.html

* Docker Hub: https://hub.docker.com/repository/docker/itauveiculos/itau/general


## Ferramentas necessárias

- JDK 17
- IDE de sua preferência
- Docker

## Como executar?

1. Clonar o repositório:

```sh
git clone https://github.com/duquedotdev/itau-veiculos.git
```

2. Subir o banco de dados PostgreSQL com Docker:

```shell
docker-compose up -d
```

3. Executar as migrações do PostgreSQL com o Flyway:

```shell
./gradlew flywayMigrate
```

4. Executar a aplicação como SpringBoot app:

```shell
  ./gradlew bootRun
``` 

> Também é possível executar como uma aplicação Java através do
> método main() na classe Main.java

## Banco de dados

O banco de dados principal é um PostgreSQL e para subir localmente vamos utilizar o
Docker. Execute o comando a seguir para subir o PostgreSQL:

```shell
docker-compose up -d
```

Pronto! Aguarde que em instantes o PostgreSQL irá estar pronto para ser consumido
na porta 5432.

### Migrações do banco de dados com Flyway

#### Executar as migrações

Caso seja a primeira vez que esteja subindo o banco de dados, é necessário
executar as migrações SQL com a ferramenta `flyway`.
Execute o comando a seguir para executar as migrações:

```shell
./gradlew flywayMigrate
```

Pronto! Agora sim o banco de dados PostgreSQL está pronto para ser utilizado.

<br/>

#### Limpar as migrações do banco

É possível limpar (deletar todas as tabelas) seu banco de dados, basta
executar o seguinte comando:

```shell
./gradlew flywayClean
```

MAS lembre-se: "Grandes poderes, vem grandes responsabilidades".

<br/>

#### Reparando as migrações do banco

Existe duas maneiras de gerar uma inconsistência no Flyway deixando ele no estado de reparação:

1. Algum arquivo SQL de migração com erro;
2. Algum arquivo de migração já aplicado foi alterado (modificando o `checksum`).

Quando isso acontecer o flyway ficará em um estado de reparação
com um registro na tabela `flyway_schema_history` com erro (`sucesso = 0`).

Para executar a reparação, corrija os arquivos e execute:

```shell
./gradlew flywayRepair
```

Com o comando acima o Flyway limpará os registros com erro da tabela `flyway_schema_history`,
na sequência execute o comando FlywayMigrate para tentar migrar-los novamente.

<br/>

#### Outros comandos úteis do Flyway

Além dos comandos já exibidos, temos alguns outros muito úteis como o info e o validate:

```shell
./gradlew flywayInfo
./gradlew flywayValidate
```

Para saber todos os comandos disponíveis: [Flyway Gradle Plugin](https://flywaydb.org/documentation/usage/gradle/info)

<br/>

#### Para executar os comandos em outro ambiente

Lá no `build.gradle` configuramos o Flyway para lêr primeiro as variáveis de
ambiente `FLYWAY_DB`, `FLYWAY_USER` e `FLYWAY_PASS` e depois usar um valor padrão
caso não as encontre. Com isso, para apontar para outro ambiente basta sobrescrever
essas variáveis na hora de executar os comandos, exemplo:

```shell
FLYWAY_DB=jdbc:postgres://prod:5432/itau FLYWAY_USER=root FLYWAY_PASS=123h1hu ./gradlew flywayValidate
```

### Executando com Docker

Para rodar a aplicação localmente com Docker, iremos utilizar o `docker compose` e necessita de apenas três passos:
<br/>

#### 1. Gerando o artefato produtivo (jar)

Para gerar o artefato produtivo, basta executar o comando:

```
./gradlew bootJar
```

<br/>

#### 2. Executando os containers independentes

Para executar o PostgreSQL, basta executar o comando abaixo.

```
docker-compose up -d
```

<br/>

#### 3. Executando a aplicação junto dos outros containers

Depois de visualizar que os demais containers estão de pé, para rodar sua aplicação junto basta executar o comando:

```
docker-compose --profile app up -d
```

> **Obs.:** Caso necessite rebuildar a imagem de sua aplicação é necessário um comando adicional:
>```
>docker compose build --no-cache app
>```

#### Parando os containers

Para encerrar os containers, basta executar o comando:

```
docker compose --profile app stop
```

#### Fun Facts / Curiosidades

- Escolhi usar o Java 17 por ser a versão mais recente e que possui melhorias de performance e segurança.
- Escolhi usar o Spring Boot por ser o framework mais utilizado no mercado e que possui uma curva de aprendizado menor.
- Escolhi usar o PostgreSQL por ser um banco de dados relacional e que possui uma curva de aprendizado menor.
- Escolhi usar o Docker por ser uma ferramenta que facilita a execução de aplicações em ambientes isolados.
- Escolhi usar o Flyway por ser uma ferramenta que facilita a execução de migrações de banco de dados.
- Escolhi usar o Gradle por ser uma ferramenta que facilita a execução de tarefas e que possui uma curva de aprendizado menor.
- Escolhi usar o JUnit por ser uma ferramenta que facilita a execução de testes unitários.
- Escolhi usar o Mockito por ser uma ferramenta que facilita a execução de testes unitários.
- Escolhi usar o Vavr pois é uma biblioteca que facilita a programação funcional.
