/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.fachada;

import br.com.lacetecnologia.rpgdatabase.dao.ParametroDAO;
import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.modelo.Parametro;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class ParametroFachada {
    @EJB
    private ParametroDAO dao;
    
    public Parametro porNome(ParametroNome nome){
        return dao.porNome(nome);
    }
    
    public void inserir(ParametroNome nome, String valor){
        Parametro novo = new Parametro();
        novo.setNome(nome);
        novo.setValor(valor);
        dao.inserir(novo);
    }
    
   
}
