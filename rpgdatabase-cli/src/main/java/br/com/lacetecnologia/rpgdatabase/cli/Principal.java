package br.com.lacetecnologia.rpgdatabase.cli;

import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.modelo.Parametro;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leticia Sena
 */
public class Principal {
    
    public static void main(String[] args){
        String usuario = "lecmsena@gmail.com";
        String senha = "let123";
        
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(usuario, senha);
        Client cliente = ClientBuilder.newBuilder().register(feature).build();
        WebTarget alvo = cliente.target("http://162.212.130.143/RPGDatabase/webapi");
        Parametro parm = alvo.path("/parametro/"+ParametroNome.CHAVE_CRIPTOGRAFIA).request().get(Parametro.class);
        System.out.println("Parm:"+parm.toJSON());
        
    }
}
