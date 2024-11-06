
insert into produto (descricao, valor)values ('Caderno', 11.10);
insert into produto (descricao, valor)values ('Caneta', 2.00);
insert into produto (descricao, valor)values ('Lápis', 2.10);
insert into produto (descricao, valor)values ('Borracha', 3.00);
insert into produto (descricao, valor)values ('Papel', 20.50);
insert into produto (descricao, valor)values ('Mochila', 120.00);
insert into produto (descricao, valor)values ('Régua', 4.30);
insert into produto (descricao, valor)values ('Grampeado', 12.00);
insert into produto (descricao, valor)values ('Marca texto', 7.00);
insert into produto (descricao, valor)values ('Cola', 6.30);
insert into produto (descricao, valor)values ('Tesoura', 8.00);

insert into venda(data_venda) values('2022-12-31 23.59.59');
insert into venda(data_venda) values('2023-12-01 23.59.59');
insert into venda(data_venda) values('2024-10-10 23.59.59');
insert into venda(data_venda) values('2024-09-30 23.59.59');

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

