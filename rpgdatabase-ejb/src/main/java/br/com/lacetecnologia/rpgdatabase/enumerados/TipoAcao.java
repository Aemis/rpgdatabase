/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.enumerados;

/**
 *
 * @author Leticia Sena
 */
public enum TipoAcao {
    DIALOGO("D"),ACAO("A"),NARRATIVA("N");
    
    private String valor;
    
    private TipoAcao(String tipoAcao){
        this.valor = tipoAcao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
