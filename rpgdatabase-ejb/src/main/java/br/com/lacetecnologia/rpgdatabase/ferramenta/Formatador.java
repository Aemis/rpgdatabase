/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.ferramenta;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leticia Sena
 */
@SessionScoped
@ManagedBean(name="formatador")
public class Formatador implements Serializable{
    
    public String formatarDataHora(Date entrada){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return sdf.format(entrada);
    }
    
    public String formatarBooleano(Boolean valor){
        return (valor)?"Sim":"Não";
    }
}
