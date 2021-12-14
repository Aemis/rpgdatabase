/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.comunicacao;

import br.com.lacetecnologia.rpgdatabase.cli.ArquivoConfiguracao;
import br.com.lacetecnologia.rpgdatabase.cli.ferramenta.ContadorDeLinhas;
import br.com.lacetecnologia.rpgdatabase.estrutura.ArquivoInserido;
import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistroArquivo;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistrosArquivos;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Formatador;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Leticia Sena
 */
public class ArquivosCli {
 
    public static RegistrosArquivos recuperaArquivosDoUsuario(){
        WebTarget alvo = Cliente.criarConexao().target(ArquivoConfiguracao.getInstance().getConfiguracao().getServidorConexao());
        return alvo.path("/arquivos").request().get(RegistrosArquivos.class);
    }

    public static ArquivoInserido cadastrarArquivo(File arquivo) throws IOException, Exception {
        RegistroArquivo novo = new RegistroArquivo();
        novo.setNome(arquivo.getName());
        novo.setCaminhoOrigem(arquivo.getAbsolutePath());
        novo.setDataHora(Formatador.formatarDateTimeParaString(new Date(arquivo.lastModified())));
        novo.setTamanho(BigInteger.valueOf(arquivo.length()));
        novo.setQtdLinhas(ContadorDeLinhas.contarLinhasArquivo(arquivo));
            
        WebTarget alvo = Cliente.criarConexao().target(ArquivoConfiguracao.getInstance().getConfiguracao().getServidorConexao());
        Response response = alvo.path("/arquivos").request().post(Entity.entity(novo, MediaType.APPLICATION_JSON));
        
        if(response.getStatus() == 201)
            return (ArquivoInserido) response.readEntity(ArquivoInserido.class);
        else {
            Erro resultado = (Erro) response.readEntity(Erro.class);
            throw new Exception(resultado.getNome() + "."+ resultado.getDescricao());
        }
    }
    
   
}
