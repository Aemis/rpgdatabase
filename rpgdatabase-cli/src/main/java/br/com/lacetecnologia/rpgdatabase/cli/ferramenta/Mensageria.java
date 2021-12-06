/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.ferramenta;

import br.com.lacetecnologia.rpgdatabase.ferramenta.Formatador;
import java.util.Date;

/**
 *
 * @author Leticia Sena
 */
public class Mensageria {

    public static void mostrarMensagemErroNaTela(String name, String mensagem) {
        Formatador format = new Formatador();
        System.err.println("["+format.formatarDataHora(new Date())+"] "+name+ ":"+ mensagem);
    }
    
}
