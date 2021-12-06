/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.manager;

import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.fachada.UsuarioFachada;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Messageria;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leticia Sena
 */
@SessionScoped
@ManagedBean(name = "loginMng")
public class LoginManager implements Serializable{
    @EJB
    private UsuarioFachada fachada;
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String login(){
        try{
            if(fachada.loginWEB(usuario, senha)){
                return "/logado/home";
            }
        }catch(Exception e){
            Messageria.mostrarMensagemErro("Houve um erro ao tentar executar o login:"+e.getMessage());
        }
        return "";
    }
    
    public void inicializa(){
        try{
            fachada.inicializa();
        } catch(Exception e){
            Messageria.mostrarMensagemErro(e.getLocalizedMessage());
        }
    }
    
    public Sessao getSessao(){
        return Sessao.getInstance();
    }
    
    public String encerrarSessao(){
        Sessao.getInstance().encerrarSessao();
        return "/index.xhtml";
    }
    
    public void verificaSessaoAtiva(){
        if(Sessao.getInstance().estaAtiva()){
            
        }else{
            
        }
    }
    
}
