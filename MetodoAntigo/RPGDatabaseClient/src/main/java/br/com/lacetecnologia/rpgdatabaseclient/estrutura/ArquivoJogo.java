package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.nio.file.attribute.FileTime;

/**
 *  Arquivo de Jogo que é encontrado na pasta base
 * @author leticiasena
 */
public class ArquivoJogo {
    private String nomeJogo;
    private String caminhoArquivo;
    private FileTime dataCriacao;
    private FileTime dataUltimaModificacao;
    private Long tamanho;
    private Jogo jogo;

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

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    
  
    public String toString(){
        
        return "Nome do Jogo:"+nomeJogo+";"+
                "Caminho: "+caminhoArquivo + ";"+
                "Criado em: "+dataCriacao.toString()+";"+
                "Última modificação: "+ dataUltimaModificacao.toString()+";"+
                "Tamanho: "+tamanho.toString()+
                "Jogo: "+ jogo;
    }
}
