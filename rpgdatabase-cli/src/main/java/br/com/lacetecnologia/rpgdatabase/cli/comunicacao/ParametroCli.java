/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.comunicacao;

import br.com.lacetecnologia.rpgdatabase.cli.ArquivoConfiguracao;
import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.modelo.Parametro;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Leticia Sena
 */
public class ParametroCli {
    
    public static Parametro recuperaParametro(ParametroNome nome){
        WebTarget alvo = Cliente.criarConexao().target(ArquivoConfiguracao.getInstance().getConfiguracao().getServidorConexao());
        return alvo.path("/parametro/"+nome).request().get(Parametro.class);
    }
}
