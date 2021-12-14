/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.manager;

import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.fachada.ApelidoFachada;
import br.com.lacetecnologia.rpgdatabase.modelo.Apelido;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leticia Sena
 */
@SessionScoped
@ManagedBean(name = "apelidoMng")
public class ApelidoManager implements Serializable {
    @EJB
    private ApelidoFachada fachada;
    
    public String getPaginaListagem(){
        return "/logado/apelido/apelidolista.xhtml";
    }
    
    public List<Apelido> listaApelido(){
        return fachada.buscarTodos(Sessao.getInstance().getUsuarioLogado().getCodigo());
    }
    
    
}
