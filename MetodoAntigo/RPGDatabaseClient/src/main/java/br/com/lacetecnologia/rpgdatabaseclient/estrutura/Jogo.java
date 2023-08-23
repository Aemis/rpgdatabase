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
        String retorno = "Jogo "+nome+" iniciado em "+
                ((inicio != null)?inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")):"Sem data")+
                " e finalizado em "+((fim != null)?fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")):"Sem data")+
                " contendo " + this.sessoes.size() + " sessões e contando como jogadores: " ;
                for(String nome:jogadores)
                    retorno += nome +", ";
                retorno += " ou seja, "+jogadores.size()+" jogadores no total.";
        return retorno;
    }

    public void addJogadores(List<String> jogadores) {
        boolean existe = false;
        for(String jogador: jogadores){
            for(String cadastrado:this.jogadores){
                if(cadastrado.equalsIgnoreCase(jogador)){
                    existe = true;
                    break;
                }
            }
            if(!existe){
                this.jogadores.add(jogador);
            }
        }
    }
}
