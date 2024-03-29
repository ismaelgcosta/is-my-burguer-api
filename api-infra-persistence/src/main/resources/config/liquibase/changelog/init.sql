-- liquibase formatted sql
-- changeset ismyburguer:1
alter table if exists item_pedido drop constraint if exists fk_item_pedido_pedido;
drop table if exists item_pedido cascade;
drop table if exists cliente cascade;
drop table if exists pagamento cascade;
drop table if exists pedido cascade;
drop table if exists produto cascade;
drop table if exists controle_pedido cascade;
create table cliente (ativo boolean not null, cliente_id uuid not null, cpf varchar(255), email varchar(255), username varchar(255) not null, nome varchar(255), sobrenome varchar(255), primary key (cliente_id), constraint uk_cliente_username unique (username), constraint uk_cliente_email unique (email), constraint uk_cliente_cpf unique NULLS NOT DISTINCT (cpf));
create table pedido (valor_total numeric(38,2), cliente_id uuid references cliente(cliente_id), pedido_id uuid not null, status_pedido varchar(255) check (status_pedido in ('ABERTO','FECHADO','PAGO','AGUARDANDO_PAGAMENTO','PAGAMENTO_NAO_AUTORIZADO','RECEBIDO','EM_PREPARACAO','PRONTO','FINALIZADO')), primary key (pedido_id));
create table produto (ativo boolean not null, preco numeric(38,2) not null, produto_id uuid not null, categoria varchar(255) not null check (categoria in ('LANCHE','ACOMPANHAMENTO','BEBIDA','SOBREMESA')), descricao varchar(255), primary key (produto_id));
create table pagamento (valor_total numeric(38,2), pagamento_id uuid not null, pedido_id uuid references pedido(pedido_id), forma_pagamento varchar(255) check (forma_pagamento in ('MERCADO_PAGO')), qr_code varchar(255), status_pagamento varchar(255) check (status_pagamento in ('AGUARDANDO_CONFIRMACAO','NAO_AUTORIZADO','PAGO')), tipo_pagamento varchar(255) check (tipo_pagamento in ('QR_CODE')), primary key (pagamento_id));
create table item_pedido (preco numeric(38,2) not null, quantidade integer not null, valor_total numeric(38,2) not null, item_pedido_id uuid not null, pedido_id uuid, produto_id uuid references produto(produto_id), primary key (item_pedido_id), constraint uk_item_pedido_produto unique (pedido_id, produto_id));
alter table if exists item_pedido add constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido;
create table controle_pedido (fim_da_preparacao timestamp(6),inicio_da_preparacao timestamp(6),recebido_em timestamp(6),controle_pedido_id uuid not null,pedido_id uuid references pedido(pedido_id),status_controle_pedido varchar(255) check (status_controle_pedido in ('RECEBIDO','EM_PREPARACAO','PRONTO','RETIRADO')),primary key (controle_pedido_id));