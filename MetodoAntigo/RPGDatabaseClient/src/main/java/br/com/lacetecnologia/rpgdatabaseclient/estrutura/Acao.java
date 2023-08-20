package br.com.lacetecnologia.rpgdatabaseclient.estrutura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author leticiasena
 */
public class Acao {
    private LocalDateTime dataHora;
    private TipoAcao tipo;
    private String quem;
    private String descricao;
    
    public Acao(){}

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAcao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcao tipo) {
        this.tipo = tipo;
    }

    public String getQuem() {
        return quem;
    }

    public void setQuem(String quem) {
        this.quem = quem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString(){
        return "["+dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+"] "+
                quem +": "+ tipo + ": "+ descricao;
    }
}
