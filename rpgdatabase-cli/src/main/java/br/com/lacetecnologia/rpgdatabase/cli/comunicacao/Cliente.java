/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.comunicacao;

import br.com.lacetecnologia.rpgdatabase.cli.ArquivoConfiguracao;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author Leticia Sena
 */
public class Cliente {
    
    public static Client criarConexao(){
         HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(ArquivoConfiguracao.getInstance().getConfiguracao().getUsuario(), 
                                                                            ArquivoConfiguracao.getInstance().getConfiguracao().getSenha());
        Client cliente = ClientBuilder.newBuilder().register(feature).build();
        return cliente;
    }
    
}
