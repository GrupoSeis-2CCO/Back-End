INSERT INTO cargo (id_cargo, nome_cargo) VALUES
(1, 'Funcionario'),
(2, 'Colaborador'),
(3, 'Gestão');
    SHOW COLUMNS FROM usuario;

INSERT INTO usuario (
    nome,
    cpf,
    email,
    senha,
    data_entrada,
    ultimo_acesso,
    fk_cargo_id_cargo
) VALUES
('John Doe', '123.456.789-00', 'john@doe.com',
'$2a$10$QQPobUtOp3Gwh3P94Itu0u/e3jGNDRt6WHhIqz2TdDFpXaK6y6lw6',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

('Cintia Tanivaro', '987.654.321-00', 'cintia@tanivaro.com',
'123123',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),

('George Brito', '456.123.789-11', 'George@Brito.com',
'123123',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);
