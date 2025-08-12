
java-clientes — CRUD de Clientes (Java 17 + Swing + MySQL)




Aplicação desktop em Java 17 com Swing e JDBC/MySQL para cadastro de clientes (CRUD completo).
Projeto Maven focado em didática, boas práticas e organização em camadas (model/dao/ui).

✨ Funcionalidades
Cadastrar, listar, atualizar e excluir clientes

Tabela com atualização em tempo real

Validação simples de campos

Conexão configurável via application.properties

🧰 Stack & requisitos
Java 17 (Temurin recomendado)

Maven 3.9+

MySQL 8.x

(Opcional) VS Code com Extension Pack for Java

🗄️ Banco de dados
No MySQL Workbench, execute o script db.sql (raiz do projeto).
Isso cria o banco clientes_db, a tabela clientes e insere dados de exemplo.

Ajuste as credenciais em src/main/resources/application.properties:

properties
Copiar
Editar
db.url=jdbc:mysql://localhost:3306/clientes_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.user=SEU_USUARIO
db.password=SUA_SENHA
▶️ Como executar
Pelo Maven (garantido em qualquer ambiente):

bash
Copiar
Editar
mvn clean compile exec:java
No VS Code (opcional):

Abra a pasta do projeto (a que contém pom.xml)

Painel Maven → exec-maven-plugin → exec:java

Ou configure um launch.json apontando para com.gamardo.clientes.App

🗂️ Estrutura
css
Copiar
Editar
java-clientes/
├─ pom.xml
├─ db.sql
├─ src/
│  └─ main/
│     ├─ java/com/gamardo/clientes/
│     │  ├─ App.java
│     │  ├─ db/ConnectionFactory.java
│     │  ├─ model/Cliente.java
│     │  ├─ dao/ClienteDAO.java
│     │  └─ ui/ClienteForm.java
│     └─ resources/
│        └─ application.properties
└─ README.md
🧪 Teste rápido
Execute db.sql

Preencha application.properties

Rode mvn clean compile exec:java

Use os botões Novo / Salvar / Atualizar / Excluir / Recarregar

🛡️ Boas práticas empregadas
DAO com PreparedStatement

Separação de camadas (UI/DAO/Model)

Config externa em application.properties

Look and Feel Nimbus (UI mais agradável)

🗺️ Roadmap (próximos passos)
Validações avançadas de formulário

Paginação e busca por nome/documento

Migração para JavaFX (UI moderna)

API REST Spring Boot (Projeto 2 do portfólio)

Containerização com Docker (MySQL e app)

📜 Licença
Distribuído sob a licença MIT. Veja a seção Licença no repositório (se aplicável).
