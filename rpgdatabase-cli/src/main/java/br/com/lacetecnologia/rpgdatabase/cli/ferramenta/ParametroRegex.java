/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.ferramenta;

import br.com.lacetecnologia.rpgdatabase.cli.comunicacao.ParametroCli;
import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;

/**
 *
 * @author Leticia Sena
 */
public class ParametroRegex {
    private static ParametroRegex instancia;
    private final String regexArquivoLog;
    private final String regexNomeJogo;
    private final String regexInicioJogo;
    private final String regexFimJogo;
    private final String regexApelido;
   
    public ParametroRegex(){
        regexArquivoLog = ParametroCli.recuperaParametro(ParametroNome.REGEX_ARQUIVO_LOG).getValor();
        regexNomeJogo = ParametroCli.recuperaParametro(ParametroNome.REGEX_JOGO_NOME).getValor();
        regexInicioJogo = ParametroCli.recuperaParametro(ParametroNome.REGEX_JOGO_INICIO).getValor();
        regexFimJogo = ParametroCli.recuperaParametro(ParametroNome.REGEX_JOGO_FIM).getValor();
        regexApelido = ParametroCli.recuperaParametro(ParametroNome.REGEX_APELIDO).getValor();
    }

    public static ParametroRegex getInstance() {
      if(instancia == null) instancia = new ParametroRegex();
      return instancia;
    }

    public String getRegexArquivoLog() {
        return regexArquivoLog;
    }

    public String getRegexNomeJogo() {
        return regexNomeJogo;
    }
    
    public String getRegexInicioJogo(){
        return regexInicioJogo;
    }
    
    public String getRegexFimJogo(){
        return regexFimJogo;
    }

    public String getRegexApelido() {
        return regexApelido;
    }
    
}
