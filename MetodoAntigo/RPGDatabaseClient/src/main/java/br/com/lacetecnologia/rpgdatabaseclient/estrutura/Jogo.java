package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leticiasena
 */
public class Jogo implements Serializable{
    private String nome;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private List<String> jogadores;
    private List<Sessao> sessoes;
    private List<String> personagens;
    
    public Jogo(String nome){
        this.nome = nome;
        jogadores = new ArrayList<String>();
        sessoes = new ArrayList<Sessao>();
        personagens = new ArrayList<String>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public List<String> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<String> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public List<String> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<String> personagens) {
        this.personagens = personagens;
    }
    
    public String toString(){
        return "Jogo "+nome+" iniciado em "+
                inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+
                " e finalizado em "+fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+".";
    }
}
