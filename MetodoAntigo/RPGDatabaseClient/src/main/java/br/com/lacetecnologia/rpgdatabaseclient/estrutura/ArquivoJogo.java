package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author leticiasena
 */
public class ArquivoJogo {
    private String nomeJogo;
    private String caminhoArquivo;
    private FileTime dataCriacao;
    private FileTime dataUltimaModificacao;

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public FileTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(FileTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public FileTime getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(FileTime dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }
  
    public String toString(){
        
        return "Nome do Jogo:"+nomeJogo+";"+
                "Caminho: "+caminhoArquivo + ";"+
                "Criado em: "+dataCriacao.toString()+";"+
                "Última modificação: "+ dataUltimaModificacao.toString()+";";
    }
}
