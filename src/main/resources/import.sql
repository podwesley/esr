INSERT INTO COZINHA (id, nome) VALUES (1 ,'Tailandesa');
INSERT INTO COZINHA (id, nome) VALUES (2 ,'Indiana');

INSERT INTO RESTAURANTE (id, nome, taxa_frete , cozinha_id) VALUES (1 ,'Comida Brasileira' , 2.40 , 1);
INSERT INTO RESTAURANTE (id, nome, taxa_frete, cozinha_id) VALUES (2 , 'Comida Americana' , 10.00 , 2);

INSERT INTO FORMA_PAGAMENTO(id, descricao) VALUES (1,'Dinheiro');
INSERT INTO FORMA_PAGAMENTO(id, descricao) VALUES (2,'Cart�o de cr�dito');
INSERT INTO FORMA_PAGAMENTO(id, descricao) VALUES (3,'Cart�o de d�bito');

INSERT INTO PERMISSAO(id, nome, descricao) VALUES (1,'Administrativo', 'Esta � uma permiss�o admimistrativa');
INSERT INTO PERMISSAO(id, nome, descricao) VALUES (2,'Operacional', 'Esta � uma permiss�o de n�vel operacional');

INSERT INTO ESTADO (id, nome) VALUES (1, 'DISTRITO FEDERAL');
INSERT INTO ESTADO (id, nome) VALUES (2, 'GOIAS');


INSERT INTO CIDADE(id, nome, estado_id) VALUES (1, 'Riacho Fundo I' , 1);
INSERT INTO CIDADE(id, nome, estado_id) VALUES (2, 'Recanto das Emas' , 1);
