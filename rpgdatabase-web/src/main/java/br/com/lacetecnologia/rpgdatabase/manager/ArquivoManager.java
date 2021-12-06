/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.manager;

import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.fachada.ArquivoFachada;
import br.com.lacetecnologia.rpgdatabase.modelo.Arquivo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leticia Sena
 */
@SessionScoped
@ManagedBean(name = "arquivoMng")
public class ArquivoManager {
    @EJB
    private ArquivoFachada fachada;
    
    public List<Arquivo> getListaArquivos(){
        return fachada.buscarTodos(Sessao.getInstance().getUsuarioLogado().getCodigo());
    }
    
    public String getPaginaListagem(){
        return "/logado/arquivo/arquivolista.xhtml";
    }
    
}
