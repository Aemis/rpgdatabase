package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author leticiasena
 */
public class Conversa {
    private LocalDateTime timestamp;
    private String jogador;
    private String frase;
    
    public Conversa(){}
    
    public Conversa(LocalDateTime timestamp,String jogador,String frase){
        this.timestamp = timestamp;
        this.jogador = jogador;
        this.frase = frase;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
    
    public String toString(){
        return "Conversa em "+this.timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+
                " por "+this.jogador+ ": "+ this.frase;
    }
    
}
