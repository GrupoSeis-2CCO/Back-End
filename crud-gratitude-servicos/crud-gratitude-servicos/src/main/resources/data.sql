-- Inserção dos cargos
INSERT INTO cargo (nome_cargo) VALUES
('Funcionario'),
('Colaborador'),
('Gestão');

-- Inserção dos usuários
INSERT INTO usuario (
    nome,
    cpf,
    email,
    senha,
    fk_cargo,
    data_entrada,
    ultimo_acesso
) VALUES
('John Doe', '12345678900', 'john@doe.com',
'$2a$10$QQPobUtOp3Gwh3P94Itu0u/e3jGNDRt6WHhIqz2TdDFpXaK6y6lw6', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Cintia Tanivaro', '98765432100', 'cintia@tanivaro.com',
'123123', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('George Brito', '45612378911', 'George@Brito.com',
'123123', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('João Silva', '12345678901', 'joao.silva@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Maria Souza', '98765432109', 'maria.souza@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carlos Oliveira', '11122233344', 'carlos.oliveira@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ana Paula', '22233344455', 'ana.paula@email.com', 'senha123', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bruno Costa', '33344455566', 'bruno.costa@email.com', 'senha456', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fernanda Lima', '44455566677', 'fernanda.lima@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gustavo Ribeiro', '55566677788', 'gustavo.ribeiro@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Patricia Gomes', '66677788899', 'patricia.gomes@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ricardo Mello', '77788899900', 'ricardo.mello@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Juliana Dias', '88899900011', 'juliana.dias@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Lucas Fernandes', '99900011122', 'lucas.fernandes@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Camila Rocha', '00011122233', 'camila.rocha@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Thiago Martins', '11122233355', 'thiago.martins@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Aline Moreira', '22233344466', 'aline.moreira@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Vinicius Alves', '33344455577', 'vinicius.alves@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Larissa Pires', '44455566688', 'larissa.pires@email.com', 'senha123', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fábio Teixeira', '55566677799', 'fabio.teixeira@email.com', 'senha456', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Monique Duarte', '66677788800', 'monique.duarte@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Diego Lima', '77788899911', 'diego.lima@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sandra Castro', '88899900022', 'sandra.castro@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Rafael Silva', '99900011133', 'rafael.silva@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Priscila Moura', '00011122244', 'priscila.moura@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Leandro Nunes', '11122233366', 'leandro.nunes@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Beatriz Cunha', '22233344477', 'beatriz.cunha@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Eduardo Campos', '33344455588', 'eduardo.campos@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tatiane Lopes', '44455566699', 'tatiane.lopes@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Marcelo Freitas', '55566677700', 'marcelo.freitas@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sabrina Farias', '66677788811', 'sabrina.farias@email.com', 'senha123', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('André Souza', '77788899922', 'andre.souza@email.com', 'senha456', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Leticia Rezende', '88899900033', 'leticia.rezende@email.com', 'senha789', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

