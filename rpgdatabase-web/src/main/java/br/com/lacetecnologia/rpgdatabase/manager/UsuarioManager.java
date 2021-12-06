/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.manager;

import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.fachada.UsuarioFachada;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Messageria;
import br.com.lacetecnologia.rpgdatabase.modelo.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leticia Sena
 */
@SessionScoped
@ManagedBean(name = "usuarioMng")
public class UsuarioManager {
    @EJB
    private UsuarioFachada fachada;
    private Usuario usuario;
    private String password1;
    private String password2;
    
    public List<Usuario> getListaUsuario(){
        return fachada.buscarTodos();
    }
    
    public String getPaginaListagem(){
        return "/logado/usuario/usuariolista.xhtml";
    }
    
    public String getPaginaInserir(){
        usuario = new Usuario();
        usuario.setAtivo(true);
        password1 = "";
        password2 = "";
        return "/logado/usuario/usuarioinserir.xhtml";
    }
    
    public String getPaginaAlterar(){
        return "/logado/usuario/usuarioalterar.xhtml";
    }
    
    public String getPaginaAlterarSenha(){
        return "/logado/usuario/usuariotrocarsenha.xhtml";
    }
    
    public String getPaginaAlterarBarraMenu(){
        this.usuario = Sessao.getInstance().getUsuarioLogado();
        return this.getPaginaAlterar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public String inserirUsuario(){
        if(password1.equals(password2)){
            try{
                fachada.adicionar(usuario.getNome(),usuario.getEmail(),password1);
                return this.getPaginaListagem();
            }catch(Exception e){
                Messageria.mostrarMensagemErro("Houve um erro ao inserir!");
            }
        }
        return "";
    }
    
    public String alterarUsuario(){
        try{
            fachada.alterar(usuario.getCodigo(),usuario.getNome(),usuario.getEmail(),usuario.getAtivo());
            return this.getPaginaListagem();
        }catch(Exception e){
            Messageria.mostrarMensagemErro("Houve um erro ao inserir!");
        }
        return "";
    }
    
}
