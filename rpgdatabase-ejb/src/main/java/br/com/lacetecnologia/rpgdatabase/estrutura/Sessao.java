/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.estrutura;

import br.com.lacetecnologia.rpgdatabase.modelo.Usuario;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leticia Sena
 */
public class Sessao implements Serializable{
    private static Sessao instancia;
    
    public static Sessao getInstance(){
        if(instancia == null) instancia = new Sessao();
        return instancia;
    }
    
    public Sessao(){}
   
     private ExternalContext currentExternalContext(){
           if (FacesContext.getCurrentInstance() == null){
               throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
           }else{
               return FacesContext.getCurrentInstance().getExternalContext();
           }
      }
      
      public Usuario getUsuarioLogado(){
           return (Usuario) getAttribute("usuario");
      }
      
      public void setUsuario(Usuario usuario){
           setAttribute("usuario", usuario);
      }
      
      public void encerrarSessao(){   
           currentExternalContext().invalidateSession();
      }
      
      public Object getAttribute(String nome){
           return currentExternalContext().getSessionMap().get(nome);
      }
      
      public void setAttribute(String nome, Object valor){
           currentExternalContext().getSessionMap().put(nome, valor);
      }
      
      public boolean estaAtiva(){
          return (getAttribute("usuario") != null);
      }
   
}
