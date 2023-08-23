package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Grava informação de leitura de Sessão 
 * @author leticiasena
 */
public class Sessao implements Serializable{
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private List<String> jogadores;
    private List<Acao> fatos;
    
    public Sessao(){
        jogadores = new ArrayList<String>();
        fatos = new ArrayList<Acao>();
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

    public List<Acao> getFatos() {
        return fatos;
    }

    public void setFatos(List<Acao> fatos) {
        this.fatos = fatos;
    }
    
    public void addJogador(String jogador){
        boolean existe = false;
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
    
    @Override
    public String toString(){
        return "Sessão iniciada em "+inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+ " e finalizada em "+
                fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+ " com "+jogadores.size()+ " jogadores e "+ fatos.size()+ " fatos ocorridos.";
    }
    
    
}
