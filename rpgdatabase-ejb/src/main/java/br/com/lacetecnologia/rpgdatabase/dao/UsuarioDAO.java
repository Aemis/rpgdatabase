/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.dao;

import br.com.lacetecnologia.rpgdatabase.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class UsuarioDAO {
    @PersistenceContext
    private EntityManager em;
    
    public Usuario porEmail(String email){
        return getUsuario("Usuario.findByEmail","email",email);
    }
    
    public Usuario porCodigo(Integer codigo){
        return getUsuario("Usuario.findByCodigo","codigo",codigo);
    }
    
    private Usuario getUsuario(String query,String parametro,Object valor){
        List<Usuario> resultado = em.createNamedQuery(query).setParameter(parametro, valor).getResultList();
        if(resultado.size() > 0) return resultado.get(0);
        return new Usuario();
    }
    
    public void alterar(Usuario usuario){
        em.merge(usuario);
    }        
    
    public void inserir(Usuario usuario){
        em.persist(usuario);
    }
    
    public List<Usuario> buscarTodos(){
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
}
