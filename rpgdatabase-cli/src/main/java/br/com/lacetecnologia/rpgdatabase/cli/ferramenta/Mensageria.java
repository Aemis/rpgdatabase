/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.ferramenta;

import br.com.lacetecnologia.rpgdatabase.cli.ArquivoConfiguracao;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Formatador;
import java.util.Date;

/**
 *
 * @author Leticia Sena
 */
public class Mensageria {

    public static void mostrarMensagemErroNaTela(String name, String mensagem) {
        System.err.println("["+Formatador.formatarDateTimeParaString(new Date())+"] "+name+ ":"+ mensagem);
    }

    public static void mostrarMensagemAbertura() {
        System.out.println("*****************************************************************");
        System.out.println("**************** RPG DATABASE - VERSÃO REMOTA *******************");
        System.out.println("*****************************************************************");
        System.out.println("Executando em: "+ Formatador.formatarDateTimeParaString(new Date()));
        System.out.println("Carregando com config disponível em: "+ ArquivoConfiguracao.getInstance().getNomeArquivoCfg());
        System.out.println("Servidor: "+ ArquivoConfiguracao.getInstance().getConfiguracao().getServidorConexao());
        if(ArquivoConfiguracao.getInstance().getConfiguracao().getUsuario().equals("ADICIONE AQUI SEU USUARIO")){
            Mensageria.mostrarMensagemErroNaTela(Mensageria.class.getName(), "Configuração não realizada. Por favor, configure seu usuário e senha para executar a aplicação.");
            System.exit(0);
        }else{
            System.out.println("Bem vindo, "+ArquivoConfiguracao.getInstance().getConfiguracao().getUsuario()+ "!");
        }
    }

    public static void mostrarMensagemNaTela(String mensagem) {
        System.out.println("["+Formatador.formatarDateTimeParaString(new Date())+"] "+mensagem);
    }
    
}
