/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.manager;

import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.fachada.JogoFachada;
import br.com.lacetecnologia.rpgdatabase.modelo.Jogo;
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
@ManagedBean(name = "jogoMng")
public class JogoManager implements Serializable{
    @EJB 
    private JogoFachada fachada;
    
    public String getPaginaListagem(){
        return "/logado/jogo/jogolista.xhtml";
    }
    
    public List<Jogo> listaJogo(){
        return fachada.buscarTodos(Sessao.getInstance().getUsuarioLogado().getCodigo());
    }
}
