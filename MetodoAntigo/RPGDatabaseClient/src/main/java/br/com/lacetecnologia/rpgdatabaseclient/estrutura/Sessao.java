package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Grava informação de leitura de Sessão 
 * @author leticiasena
 */
public class Sessao implements Serializable{
    private LocalDate inicio;
    private LocalDate fim;
    private List<String> jogadores;
    private List<Acao> fatos;
    
    public Sessao(){}

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public List<String> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<String> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Acao> getFatos() {
        return fatos;
    }

    public void setFatos(List<Acao> fatos) {
        this.fatos = fatos;
    }
    
    @Override
    public String toString(){
        return "Sessão iniciada em "+inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+ " e finalizada em "+
                fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+ " com "+jogadores.size()+ " jogadores e "+ fatos.size()+ " fatos ocorridos.";
    }
    
    
}
