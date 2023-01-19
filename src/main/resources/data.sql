INSERT INTO estado (nome, uf) 
    VALUES ('BAHIA', 'BA'), ('RIO DE JANEIRO', 'RJ'), ('SANTA CATARINA', 'SC');

INSERT INTO cidade (nome, estado_id) 
    VALUES ('Feira de Santana', 1), ('Serra Preta', 1), ('Baixa Grande', 1);

INSERT INTO cidade (nome, estado_id) 
    VALUES ('Belford Roxo', 2), ('Duque de Caxias', 2), ('Cabo Frio', 2);

INSERT INTO cidade (nome, estado_id) 
    VALUES ('Joinvile', 3), ('Blumenau', 3), ('Brusque', 3);

INSERT INTO pessoa (nome, data_nascimento) 
    VALUES ('Peter Parker', '2000-06-17'), ('Bruce Wayne', '1982-03-22'), ('Clark Kent', '1975-01-20');

INSERT INTO endereco (principal, logradouro, cep, numero, cidade_id, pessoa_id) 
    VALUES (true, 'Rua Boa Vizinhança', '11222333', '12-A', 2, 1);

INSERT INTO endereco (principal, logradouro, cep, numero, cidade_id, pessoa_id) 
    VALUES (true, 'Rua Gotham City', '66000222', '100', 4, 2);

INSERT INTO endereco (principal, logradouro, cep, numero, cidade_id, pessoa_id) 
    VALUES (true, 'Rua Smallville', '66555000', '10', 9, 3), (false, 'Rua Planeta Diário', '13444777', '50', 7, 3);