-- Criação de raças
INSERT INTO Raca(nome, traco_racial) VALUES ('Elfo', 'Precisão élfica: facilidade em acertar alvos');
INSERT INTO Raca(nome, traco_racial) VALUES ('Humano', 'Versatilidade humana: pode escolher uma perícia extra');
INSERT INTO Raca(nome, traco_racial) VALUES ('Halfling', 'Sorte do halfling: pode rolar novamente um dado que deu errado');

-- Criação de classes
INSERT INTO Classe(nome, habilidade) VALUES ('Patrulheiro', 'Companhia animal: tem um companheiro animal');
INSERT INTO Classe(nome, habilidade) VALUES ('Mago', 'Magia arcana: pode lançar magias');
INSERT INTO Classe(nome, habilidade) VALUES ('Bardo', 'Inspiração: pode inspirar aliados para dar diferentes bônus');

-- Criação de personagens
INSERT INTO Personagem(nome, ca, id_raca, id_classe) VALUES ('Drizzt', 15, 1, 1);
INSERT INTO Personagem(nome, ca, id_raca, id_classe) VALUES ('Mordekainen', 13, 2, 2);
INSERT INTO Personagem(nome, ca, id_raca, id_classe) VALUES ('Bigby', 12, 3, 3);

-- Criação de perícias
INSERT INTO Pericia(nome, bonus) VALUES ('Furtividade', 2);
INSERT INTO Pericia(nome, bonus) VALUES ('Percepção', 3);
INSERT INTO Pericia(nome, bonus) VALUES ('Arcanismo', 1);

-- Criação de perícias de personagens
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (1, 1);
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (1, 2);
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (2, 2);
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (2, 3);
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (3, 1);
INSERT INTO Personagem_Pericia(id_personagem, id_pericia) VALUES (3, 3);

-- Criação de itens
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Morte gélida', 1, 1);
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Cajado de poder', 1, 2);
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Adaga', 1, 3);
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Pantera de obsidiana', 0, 1);
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Tomo de magia', 0, 2);
INSERT INTO Item(nome, tipo, id_personagem) VALUES ('Alaúde', 0, 3);


-- Criação de armas
INSERT INTO Arma(dano, id_item) VALUES (10, 1);
INSERT INTO Arma(dano, id_item) VALUES (5, 2);
INSERT INTO Arma(dano, id_item) VALUES (3, 3);

-- Criação de ferramentas
INSERT INTO Ferramenta(descricao, id_item) VALUES ('Estátua de pantera', 4);
INSERT INTO Ferramenta(descricao, id_item) VALUES ('Livro de magias', 5);
INSERT INTO Ferramenta(descricao, id_item) VALUES ('Instrumento musical', 6);


-- Selects
-- 1. Listar todos os personagens
SELECT * FROM Personagem;

-- 2. Listar todas as raças
SELECT * FROM Raca;

-- 3. Listar todas as classes
SELECT * FROM Classe;

-- 4. Listar todas as perícias
SELECT * FROM Pericia;

-- 5. Listar todos os itens
SELECT * FROM Item;

-- 6. Listar todas as armas
SELECT * FROM Arma;

-- 7. Listar todas as ferramentas
SELECT * FROM Ferramenta;

-- 8. Listar todas as perícias de um personagem, mostrando o nome do personagem e o nome da perícia
SELECT p.nome, pe.nome FROM Personagem p
INNER JOIN Personagem_Pericia pp ON p.id = pp.id_personagem
INNER JOIN Pericia pe ON pe.id = pp.id_pericia;

-- 9. Listar todos os itens de um personagem, mostrando o nome do personagem e todas as informações do item (nome e dano para armas, nome e descrição para ferramentas)
SELECT p.nome, i.nome, a.dano, f.descricao FROM Personagem p
INNER JOIN Item i ON p.id = i.id_personagem
LEFT JOIN Arma a ON a.id_item = i.id
LEFT JOIN Ferramenta f ON f.id_item = i.id;

-- 10. Listar todos os itens, mostrando o nome do item e suas informações específicas (dano para armas, descrição para ferramentas)
SELECT i.nome, a.dano, f.descricao FROM Item i
LEFT JOIN Arma a ON a.id_item = i.id
LEFT JOIN Ferramenta f ON f.id_item = i.id;

-- TEST CASCADES
-- Apagar um personagem apaga suas perícias da tabela Personagem_Pericia
SELECT * FROM Personagem_Pericia;
DELETE FROM Personagem WHERE id = 1;
SELECT * FROM Personagem_Pericia;

-- Apagar uma perícia apaga suas referências na tabela Personagem_Pericia
SELECT * FROM Personagem_Pericia;
DELETE FROM Pericia WHERE id = 1;
SELECT * FROM Personagem_Pericia;

-- Apagar um item apaga suas referências nas tabelas Arma e Ferramenta
SELECT * FROM Arma;
SELECT * FROM Ferramenta;
DELETE FROM Item WHERE id = 2;
SELECT * FROM Arma;
SELECT * FROM Ferramenta;