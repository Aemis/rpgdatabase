/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/** 
 * Criando tabelas iniciais
 * Author:  Leticia Sena
 * Created: 21/10/2021
 */

CREATE TABLE arquivo(
    arquivocodigo                   SERIAL          PRIMARY KEY,
    arquivonome                     VARCHAR(255)    NOT NULL,
    arquivocaminhoorigem            VARCHAR(255)    NOT NULL,
    arquivocaminhoarmazenamento     VARCHAR(255)    NOT NULL,
    arquivodatahora                 TIMESTAMP       NOT NULL,
    arquivotamanho                  DECIMAL         NOT NULL,
    arquivodatahoraentrada          TIMESTAMP       NOT NULL,
    arquivodatahoraprocessamento    TIMESTAMP       NULL,
    arquivogravacao                 TIMESTAMP       NULL,
    arquivofinalizacao              TIMESTAMP       NULL
);

CREATE TABLE apelido(
    apelidocodigo           SERIAL         PRIMARY KEY,
    apelidonome             VARCHAR(255)   NOT NULL,
    apelidodataInicioUso    DATE           NOT NULL
);


CREATE TABLE jogo(
    jogocodigo              SERIAL        PRIMARY KEY,
    jogonome                VARCHAR(255)  NOT NULL, 
    jogodatahorainicio      TIMESTAMP     NOT NULL,
    jogodatahoraultimoenvio TIMESTAMP     NOT NULL,
    jogoestafinalizado      BOOLEAN       NOT NULL
);

CREATE TABLE sessao(
    sessaocodigo            SERIAL PRIMARY KEY,
    sessaodatahorainicio    TIMESTAMP NOT NULL,
    sessaodatahorafim       TIMESTAMP NOT NULL,
    sessaojogo              INTEGER NOT NULL,
    sessaonumerosessaojogo  INTEGER,
    FOREIGN KEY (sessaojogo) REFERENCES jogo(jogocodigo));

CREATE TABLE acao(
    acaocodigo      SERIAL PRIMARY KEY,
    acaosessao      INTEGER NOT NULL,
    acaodatahora    TIMESTAMP NOT NULL,
    acaoapelido     INTEGER NOT NULL, 
    acaotipo        CHARACTER(1) NOT NULL, 
    acaodescricao   TEXT NOT NULL,
    FOREIGN KEY (acaosessao) REFERENCES sessao(sessaocodigo),
    FOREIGN KEY (acaoapelido) REFERENCES apelido(apelidocodigo));

CREATE TABLE jogoarquivo(
    jogoarquivocodigo   SERIAL   PRIMARY KEY,
    jogoarquivojogo     INTEGER  NOT NULL,
    jogoarquivoarquivo  INTEGER  NOT NULL,
    FOREIGN KEY (jogoarquivojogo) REFERENCES jogo(jogocodigo),
    FOREIGN KEY (jogoarquivoarquivo) REFERENCES arquivo(arquivocodigo));