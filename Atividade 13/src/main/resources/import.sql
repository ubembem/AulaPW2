
INSERT INTO role (nome) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

insert into produto (imagem_url, descricao, valor)values ('imagens/caderno.png', 'Caderno', 11.10);
insert into produto (imagem_url, descricao, valor)values ('imagens/caneta.png', 'Caneta', 2.00);
insert into produto (imagem_url, descricao, valor)values ('imagens/lapis.png', 'Lápis', 2.10);
insert into produto (imagem_url, descricao, valor)values ('imagens/borracha.png', 'Borracha', 3.00);
insert into produto (imagem_url, descricao, valor)values ('imagens/papel.png', 'Papel', 20.50);
insert into produto (imagem_url, descricao, valor)values ('imagens/mochila.png', 'Mochila', 120.00);
insert into produto (imagem_url, descricao, valor)values ('imagens/governante.png', 'Régua', 4.30);
insert into produto (imagem_url, descricao, valor)values ('imagens/grampeador.png', 'Grampeado', 12.00);
insert into produto (imagem_url, descricao, valor)values ('imagens/marca-texto.png', 'Marca texto', 7.00);
insert into produto (imagem_url, descricao, valor)values ('imagens/cola.png', 'Cola', 6.30);
insert into produto (imagem_url, descricao, valor)values ('imagens/tesoura.png', 'Tesoura', 8.00);

insert into pessoa(tipo, cpf,email, nome, telefone) values('F', '012.409.591-12', 'joseantonio@email.com', 'José Antônio', '(63)98467-5858');
insert into pessoa(tipo, cpf,email, nome, telefone) values('F', '798.101.310-28', 'antoniocarlos@email.com','Antônio Carlos', '(63)989898989');
insert into pessoa(tipo, cpf,email, nome, telefone) values('F', '357.218.721-04', 'ritasilva@email.com', 'Rita Silva', '(63)987664433');
insert into pessoa(tipo, cnpj,email, RAZAO_SOCIAL, telefone) values('J', '07.305.709/00001-02', 'rjconstrutora@email.com','RJ Construtora', '(63)3123-1223');

INSERT INTO usuario (login, password, PESSOA_ID) VALUES ('admin', '$2a$10$iGIX0LYHa03eoXEs232fC.046eexCmjy5rRGXHAzPoPUm1yUJ67lC', 1); -- Senha: "admin"
INSERT INTO usuario (login, password, PESSOA_ID) VALUES ('antonio', '$2a$10$iGIX0LYHa03eoXEs232fC.046eexCmjy5rRGXHAzPoPUm1yUJ67lC', 2);  -- Senha: "123"
INSERT INTO usuario (login, password, PESSOA_ID) VALUES ('Rita', '$2a$10$iGIX0LYHa03eoXEs232fC.046eexCmjy5rRGXHAzPoPUm1yUJ67lC', 3);  -- Senha: "123"
INSERT INTO usuario (login, password, PESSOA_ID) VALUES ('rj', '$2a$10$iGIX0LYHa03eoXEs232fC.046eexCmjy5rRGXHAzPoPUm1yUJ67lC', 4);  -- Senha: "123"
--UPDATE usuario SET PASSWORD = '$2a$10$ExemploDeSenhaCriptografada' WHERE login = 'admin'; --Senha "123
INSERT INTO USUARIO_ROLES (USUARIOS_ID, ROLES_ID) VALUES (1, 1); -- Admin recebe papel ADMIN
INSERT INTO USUARIO_ROLES (USUARIOS_ID, ROLES_ID) VALUES (2, 2); -- User recebe papel USER
INSERT INTO USUARIO_ROLES (USUARIOS_ID, ROLES_ID) VALUES (3, 2); -- User recebe papel USER
INSERT INTO USUARIO_ROLES (USUARIOS_ID, ROLES_ID) VALUES (4, 2); -- User recebe papel USER

insert into venda(data_venda, cliente_id,FORMA_PAGAMENTO_ENUM) values('2022-12-31 23.59.59', 1, 'DINHEIRO');
insert into venda(data_venda, cliente_id,FORMA_PAGAMENTO_ENUM) values('2023-12-01 23.59.59', 2, 'DINHEIRO');
insert into venda(data_venda, cliente_id,FORMA_PAGAMENTO_ENUM) values('2024-10-10 23.59.59', 3, 'DINHEIRO');
insert into venda(data_venda, cliente_id,FORMA_PAGAMENTO_ENUM) values('2024-09-30 23.59.59', 4, 'DINHEIRO');
--Enderecos de entrega
insert into ENDERECO_ENTREGA(venda_id, bairro, cep, cidade, estado, logradouro, numero) VALUES ( 1, 'Centro', '77.019-000', 'Palmas', 'Tocantins', 'Rua 1', '2A' );
insert into ENDERECO_ENTREGA(venda_id, bairro, cep, cidade, estado, logradouro, numero) VALUES ( 2, 'Centro', '77.987-051', 'Palmas', 'Tocantins', 'Rua 2', '100' );
insert into ENDERECO_ENTREGA(venda_id, bairro, cep, cidade, estado, logradouro, numero) VALUES ( 3, 'Centro', '77.019-350', 'Boa Vista', 'Tocantins', 'Rua 3', '300' );
insert into ENDERECO_ENTREGA(venda_id, bairro, cep, cidade, estado, logradouro, numero) VALUES ( 4, 'Centro', '77.310-001', 'Palmas', 'Tocantins', 'Rua 4', '727' );

insert into item_venda(quantidade, id_produto, venda_id) values ( 3, 1, 1);
insert into item_venda(quantidade, id_produto, venda_id) values ( 2, 2, 1);
insert into item_venda(quantidade, id_produto, venda_id) values ( 3, 4, 1);
insert into item_venda(quantidade, id_produto, venda_id) values ( 5, 11, 2);
insert into item_venda(quantidade, id_produto, venda_id) values ( 4, 5, 2);
insert into item_venda(quantidade, id_produto, venda_id) values ( 1, 8, 2);
insert into item_venda(quantidade, id_produto, venda_id) values ( 3, 7, 2);
insert into item_venda(quantidade, id_produto, venda_id) values ( 1, 3, 3);
insert into item_venda(quantidade, id_produto, venda_id) values ( 2, 6, 3);
insert into item_venda(quantidade, id_produto, venda_id) values ( 5, 9, 4);
insert into item_venda(quantidade, id_produto, venda_id) values ( 1, 10, 4);
insert into item_venda(quantidade, id_produto, venda_id) values ( 8, 7, 4);

