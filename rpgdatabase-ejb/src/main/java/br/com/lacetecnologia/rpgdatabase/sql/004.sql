/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Leticia Sena
 * Created: 02/12/2021
 */
ALTER TABLE arquivo ADD COLUMN arquivoqtdlinhas integer not null default 0;
ALTER TABLE arquivo ADD COLUMN arquivoultlinhalida integer not null default 0;

