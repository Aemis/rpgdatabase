/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/** Melhoria das tabelas iniciais
 * Author:  Leticia Sena
 * Created: 13/11/2021
 */

CREATE TABLE usuario(
    usuariocodigo SERIAL PRIMARY KEY,
    usuarionome VARCHAR(60) NOT NULL, 
    usuarioemail VARCHAR(255) UNIQUE NOT NULL, 
    usuariosenha VARCHAR(255) NOT NULL,
    usuarioentrada TIMESTAMP NOT NULL, 
    usuarioativo  BOOLEAN NOT NULL
);

ALTER TABLE arquivo ADD COLUMN arquivousuario INTEGER NOT NULL;
ALTER TABLE arquivo ADD FOREIGN KEY (arquivousuario) REFERENCES usuario(usuariocodigo);


