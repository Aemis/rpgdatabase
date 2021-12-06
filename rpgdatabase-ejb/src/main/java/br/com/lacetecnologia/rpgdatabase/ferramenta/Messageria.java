package br.com.lacetecnologia.rpgdatabase.ferramenta;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leticia
 */
public class Messageria {

   public static void mostrarMensagemErro(String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", mensagem));
   }
}
