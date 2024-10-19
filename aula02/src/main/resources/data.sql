create table pessoa
(
    id Long AUTO_INCREMENT PRIMARY KEY,
    nome varchar(80) not null
);

insert into pessoa (nome)values ('Maria');
insert into pessoa (nome)values ('Fagno');

create table veiculo
(
    id Long AUTO_INCREMENT PRIMARY KEY,
    marca varchar(50) not null,
    modelo varchar(50) not null,
    preco double not null,
    anoFabricacao int not null
);

insert into veiculo(marca, modelo, preco, anoFabricacao) values('Fiat', 'Marea', 5000, 2000);
insert into veiculo(marca, modelo, preco, anoFabricacao) values('Ford', 'Ka', 8000, 2007);
insert into veiculo(marca, modelo, preco, anoFabricacao) values('Ford', 'Fiesta', 10000, 2011);