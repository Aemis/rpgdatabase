package br.com.lacetecnologia.rpgdatabase.cli;

import br.com.lacetecnologia.rpgdatabase.cli.comunicacao.ArquivosCli;
import br.com.lacetecnologia.rpgdatabase.cli.comunicacao.ParametroCli;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistrosArquivos;
import br.com.lacetecnologia.rpgdatabase.cli.ferramenta.Mensageria;
import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.estrutura.ArquivoInserido;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistroArquivo;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Formatador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

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
    private RegistrosArquivos arquivosDoUsuario;
    private String regexArquivoLog;
    
    public Principal(){
        Mensageria.mostrarMensagemAbertura();
        regexArquivoLog = ParametroCli.recuperaParametro(ParametroNome.REGEX_ARQUIVO_LOG).getValor();
        if(regexArquivoLog == null){
            Mensageria.mostrarMensagemErroNaTela(Principal.class.getName(), "Não foi possível encontrar um importante parâmetro para execução. Por favor verifique com o dev.(#1)");
            System.exit(0);
        }
        arquivosDoUsuario = ArquivosCli.recuperaArquivosDoUsuario();
        if(arquivosDoUsuario == null){
            Mensageria.mostrarMensagemErroNaTela(Principal.class.getName(), "Não foi possível encontrar um importante parâmetro para execução. Por favor verifique com o dev.(#2)");
            System.exit(0);
        }
        try{
            verificarPastas();
        }catch(Exception e){
            Mensageria.mostrarMensagemErroNaTela(Principal.class.getName(), "Houve um erro ao verificar os arquivos de log. Erro:"+e.getLocalizedMessage());
            System.exit(0);
        }
        
    }
    
    public static void main(String[] args){
       Principal p = new Principal(); 
    }

    private void verificarPastas() throws IOException, UnsupportedEncodingException, ParseException, Exception {
        Mensageria.mostrarMensagemNaTela("Verificando pastas...");
        int numero = 1;
        File pasta;
        for(String caminho:ArquivoConfiguracao.getInstance().getConfiguracao().getPastaLog()){
            Mensageria.mostrarMensagemNaTela("Pasta #"+numero+": "+caminho);
            pasta = new File(caminho);
            verificarPasta(pasta);
            Mensageria.mostrarMensagemNaTela("Verificação da pasta #"+numero+" finalizada.");
            numero++;
        }
    }

    private void verificarPasta(File pasta) throws UnsupportedEncodingException, IOException, ParseException, Exception {
        if(pasta.exists() && pasta.isDirectory()){
            for(File arquivo: pasta.listFiles()){
                if(arquivo.isDirectory()){
                    Mensageria.mostrarMensagemNaTela("Sub-pasta encontrada! Sub-Pasta "+ arquivo.getAbsolutePath());
                    verificarPasta(arquivo);
                }else{
                    Mensageria.mostrarMensagemNaTela("Arquivo encontrado! Verificando arquivo:"+arquivo.getName());
                    //verifica se o arquivo é um LOG e se existe no servidor
                    if(verificaSeEhLog(arquivo)){
                        Mensageria.mostrarMensagemNaTela("Identificado arquivo de log!");
                        if(!arquivoExisteNoServidor(arquivo)){
                            Mensageria.mostrarMensagemNaTela("Arquivo não existe no servidor! Cadastrando... ");
                            ArquivoInserido ai= ArquivosCli.cadastrarArquivo(arquivo);
                            if(ai.getInseriu() && !ai.getArquivoExiste() ){
                                Mensageria.mostrarMensagemNaTela("Novo arquivo: Arquivo cadastrado com sucesso!");
                            }else{
                                if(!ai.getInseriu() && ai.getArquivoExiste())
                                    Mensageria.mostrarMensagemNaTela("Arquivo já existe no servidor.");    
                                else
                                    Mensageria.mostrarMensagemNaTela("Houve um erro ao cadastrar o arquivo! Verifique no servidor.");
                            }
                            
                        }
                    }
                }
            }
        }
    }

    private boolean arquivoExisteNoServidor(File arquivo) throws ParseException {
        boolean existe = false;
        if( arquivosDoUsuario.arquivos.size() > 0){
            for(RegistroArquivo arq:arquivosDoUsuario.getArquivos() ){
                if(arq.getNome().equalsIgnoreCase(arquivo.getName()) && 
                   arq.getTamanho().equals(new BigDecimal(arquivo.getTotalSpace())) &&
                   arq.getCaminhoOrigem().equals(arquivo.getAbsolutePath()) &&
                   (Formatador.formataStringParaDateTime(arq.getDataHora()).compareTo(new Date(arquivo.lastModified())) == 0 )){
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }

    private boolean verificaSeEhLog(File arquivo) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
        String texto = ""; 
        while((texto = reader.readLine()).isEmpty()){
            if(!texto.isEmpty()) break;
        }
        reader.close();
        return texto.matches(regexArquivoLog);
    }
}
