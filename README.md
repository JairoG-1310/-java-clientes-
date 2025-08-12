# java-clientes (CRUD Swing + MySQL)

Projeto demonstrativo em **Java 17** com **Swing** e **JDBC/MySQL**.

## Requisitos
- Java 17 (Temurin 17 recomendado)
- Maven 3.9+
- MySQL Server
- VS Code com Extension Pack for Java

## Configuração do Banco
1. Abra o MySQL Workbench e execute o arquivo `db.sql` na raiz do projeto.
2. Edite `src/main/resources/application.properties` com seu usuário e senha do MySQL.

## Executar
```bash
mvn clean compile exec:java
```

> Dica: se estiver no VS Code, abra a pasta do projeto e use o painel **Maven** para rodar a meta `exec:java`.

## Estrutura
- `com.gamardo.clientes.App` → inicializa a UI
- `db.ConnectionFactory` → cria conexões lendo `application.properties`
- `dao.ClienteDAO` → CRUD
- `ui.ClienteForm` → tela com JTable e botões
- `model.Cliente` → entidade
