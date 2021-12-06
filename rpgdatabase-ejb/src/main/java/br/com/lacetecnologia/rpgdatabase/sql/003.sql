/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Leticia Sena
 * Created: 28/11/2021
 */

create table parametro(
    parametrocodigo SERIAL PRIMARY KEY,
    parametronome Varchar(30) NOT NULL UNIQUE, 
    parametrovalor text NOT NULL
);