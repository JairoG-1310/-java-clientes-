CREATE DATABASE IF NOT EXISTS clientes_db DEFAULT CHARACTER SET utf8mb4;
USE clientes_db;
CREATE TABLE IF NOT EXISTS clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(160),
  telefone VARCHAR(40),
  documento VARCHAR(30),
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO clientes (nome, email, telefone, documento) VALUES
('Maria Silva', 'maria@example.com', '(65) 99999-0001', '111.111.111-11'),
('João Souza', 'joao@example.com', '(65) 99999-0002', '222.222.222-22');