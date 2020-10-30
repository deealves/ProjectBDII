
CREATE TABLE ProjetoBD.Endereco_Fornecedor (
                CEP VARCHAR(10) NOT NULL,
                Logradouro VARCHAR NOT NULL,
                Cidade VARCHAR NOT NULL,
                Estado VARCHAR NOT NULL,
                CONSTRAINT cep PRIMARY KEY (CEP)
);


CREATE TABLE ProjetoBD.Endereco_Pessoa (
                CEP VARCHAR(10) NOT NULL,
                Logradouro VARCHAR NOT NULL,
                Cidade VARCHAR NOT NULL,
                Estado VARCHAR NOT NULL,
                CONSTRAINT cep PRIMARY KEY (CEP)
);


CREATE TABLE ProjetoBD.Fornecedor (
                CNPJ VARCHAR NOT NULL,
                Endereco_Fornecedor_CEP VARCHAR(10) NOT NULL,
                Nome_Fantasia VARCHAR NOT NULL,
                CONSTRAINT fornecedor_pk PRIMARY KEY (CNPJ, Endereco_Fornecedor_CEP)
);


CREATE TABLE ProjetoBD.Telefone_Fornecedor (
                Telefone VARCHAR NOT NULL,
                CNPJ VARCHAR NOT NULL,
                CONSTRAINT telefone_fornecedor_pk PRIMARY KEY (Telefone, CNPJ)
);


CREATE TABLE ProjetoBD.Produto (
                Id_Produto INTEGER NOT NULL,
                Nome VARCHAR NOT NULL,
                Descricao VARCHAR NOT NULL,
                Unidade INTEGER NOT NULL,
                Quantidade INTEGER NOT NULL,
                Valor REAL NOT NULL,
                CONSTRAINT id_produto PRIMARY KEY (Id_Produto)
);


CREATE TABLE ProjetoBD.Fornce_Produto (
                CNPJ VARCHAR NOT NULL,
                Id_Produto INTEGER NOT NULL,
                CONSTRAINT fornce_produto_pk PRIMARY KEY (CNPJ, Id_Produto)
);


CREATE TABLE ProjetoBD.Pessoa (
                CPF VARCHAR(11) NOT NULL,
                CEP VARCHAR(10) NOT NULL,
                Nome VARCHAR(50) NOT NULL,
                Data_Admissao DATE NOT NULL,
                CONSTRAINT cpf PRIMARY KEY (CPF, CEP)
);


CREATE TABLE ProjetoBD.Telefone_Pessoa (
                Telefone VARCHAR NOT NULL,
                CPF VARCHAR(11) NOT NULL,
                CONSTRAINT telefone_pessoa_pk PRIMARY KEY (Telefone, CPF)
);


CREATE TABLE ProjetoBD.Moto_Boy (
                Placa_Moto VARCHAR NOT NULL,
                CPF VARCHAR(11) NOT NULL,
                CONSTRAINT placa_moto PRIMARY KEY (Placa_Moto, CPF)
);


CREATE TABLE ProjetoBD.Usuario (
                CPF VARCHAR(11) NOT NULL,
                Matricula VARCHAR NOT NULL,
                Tipo_Usuario VARCHAR NOT NULL,
                CONSTRAINT matricula PRIMARY KEY (CPF, Matricula)
);


CREATE TABLE ProjetoBD.Entrega_Produto (
                Produto_Id_Produto INTEGER NOT NULL,
                CPF VARCHAR(11) NOT NULL,
                Matricula VARCHAR NOT NULL,
                Placa_Moto VARCHAR NOT NULL,
                Numero_Pedido INTEGER NOT NULL,
                Hora TIME NOT NULL,
                Data DATE NOT NULL,
                CONSTRAINT entrega_produto_pk PRIMARY KEY (Produto_Id_Produto, CPF, Matricula, Placa_Moto)
);


CREATE TABLE ProjetoBD.Cadastro_Produto (
                Id_Usuario VARCHAR NOT NULL,
                Id_Produto INTEGER NOT NULL,
                CONSTRAINT cadastro_produto_pk PRIMARY KEY (Id_Usuario, Id_Produto)
);


CREATE TABLE ProjetoBD.Cadastro_MotoBoy (
                Id_Usuario VARCHAR NOT NULL,
                Placa_Moto VARCHAR NOT NULL,
                CPF VARCHAR(11) NOT NULL,
                CONSTRAINT id_cadastro_motoboy PRIMARY KEY (Id_Usuario, Placa_Moto, CPF)
);


CREATE TABLE ProjetoBD.Cadastro_Fornecedor (
                Id_Usuario VARCHAR NOT NULL,
                CNPJ VARCHAR NOT NULL,
                CONSTRAINT cadastro_fornecedor_pk PRIMARY KEY (Id_Usuario, CNPJ)
);


ALTER TABLE ProjetoBD.Fornecedor ADD CONSTRAINT endereco_fornecedor_fornecedor_fk
FOREIGN KEY (Endereco_Fornecedor_CEP)
REFERENCES ProjetoBD.Endereco_Fornecedor (CEP)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Pessoa ADD CONSTRAINT endereco_pessoa_pessoa_fk
FOREIGN KEY (CEP)
REFERENCES ProjetoBD.Endereco_Pessoa (CEP)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Fornce_Produto ADD CONSTRAINT fornecedor_fornce_produto_fk
FOREIGN KEY (CNPJ)
REFERENCES ProjetoBD.Fornecedor (CNPJ)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Telefone_Fornecedor ADD CONSTRAINT fornecedor_telefone_fornecedor_fk
FOREIGN KEY (CNPJ)
REFERENCES ProjetoBD.Fornecedor (CNPJ)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_Fornecedor ADD CONSTRAINT fornecedor_cadastro_fornecedor_fk
FOREIGN KEY (CNPJ)
REFERENCES ProjetoBD.Fornecedor (CNPJ)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Fornce_Produto ADD CONSTRAINT produto_fornce_produto_fk
FOREIGN KEY (Id_Produto)
REFERENCES ProjetoBD.Produto (Id_Produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Entrega_Produto ADD CONSTRAINT produto_entrega_produto_fk
FOREIGN KEY (Produto_Id_Produto)
REFERENCES ProjetoBD.Produto (Id_Produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_Produto ADD CONSTRAINT produto_cadastro_produto_fk
FOREIGN KEY (Id_Produto)
REFERENCES ProjetoBD.Produto (Id_Produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Usuario ADD CONSTRAINT pessoa_usuario_fk
FOREIGN KEY (CPF)
REFERENCES ProjetoBD.Pessoa (CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Moto_Boy ADD CONSTRAINT pessoa_moto_boy_fk
FOREIGN KEY (CPF)
REFERENCES ProjetoBD.Pessoa (CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Telefone_Pessoa ADD CONSTRAINT pessoa_telefone_pessoa_fk
FOREIGN KEY (CPF)
REFERENCES ProjetoBD.Pessoa (CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Entrega_Produto ADD CONSTRAINT moto_boy_entrega_produto_fk
FOREIGN KEY (Placa_Moto, CPF)
REFERENCES ProjetoBD.Moto_Boy (Placa_Moto, CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_MotoBoy ADD CONSTRAINT moto_boy_cadastro_motoboy_fk
FOREIGN KEY (Placa_Moto, CPF)
REFERENCES ProjetoBD.Moto_Boy (Placa_Moto, CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_Fornecedor ADD CONSTRAINT usuario_cadastro_fornecedor_fk
FOREIGN KEY (Id_Usuario)
REFERENCES ProjetoBD.Usuario (Matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_MotoBoy ADD CONSTRAINT usuario_cadastro_motoboy_fk
FOREIGN KEY (Id_Usuario)
REFERENCES ProjetoBD.Usuario (Matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Cadastro_Produto ADD CONSTRAINT usuario_cadastro_produto_fk
FOREIGN KEY (Id_Usuario)
REFERENCES ProjetoBD.Usuario (Matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ProjetoBD.Entrega_Produto ADD CONSTRAINT usuario_entrega_produto_fk
FOREIGN KEY (CPF, Matricula)
REFERENCES ProjetoBD.Usuario (CPF, Matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
