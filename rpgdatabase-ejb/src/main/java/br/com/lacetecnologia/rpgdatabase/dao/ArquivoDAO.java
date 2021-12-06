/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.dao;

import br.com.lacetecnologia.rpgdatabase.modelo.Arquivo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class ArquivoDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Arquivo> buscarTodos(Integer codigoUsuario){
        return em.createNamedQuery("Arquivo.buscarPorUsuario").setParameter("codigo", codigoUsuario).getResultList();
    }
}
