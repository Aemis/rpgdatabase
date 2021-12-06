/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.dao;

import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.modelo.Parametro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class ParametroDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(Parametro parm){
        em.persist(parm);
    }
    
    public void alterar(Parametro parm){
        em.merge(parm);
        em.flush();
    }
    
    public void excluir(Parametro parm){
        em.remove(parm);
    }
    
    public Parametro porNome(ParametroNome nome){
        List<Parametro> resultado = em.createNamedQuery("Parametro.findByNome").setParameter("nome", nome).getResultList();
        if(resultado.size() > 0) return resultado.get(0);
        return null;
    }
    
}
