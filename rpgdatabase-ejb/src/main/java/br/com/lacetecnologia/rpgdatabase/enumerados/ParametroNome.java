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
public enum ParametroNome {
    CHAVE_CRIPTOGRAFIA("Chave_Criptografia");
    
    private String valor;
    
    private ParametroNome(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    /**
     *
     * @param valor
     * @return
     */
    public static ParametroNome getParametro(String valor){
        switch(valor){
            case "Chave_Criptografia": 
                return ParametroNome.CHAVE_CRIPTOGRAFIA;
        }
        return null;
    }
    
    
}
