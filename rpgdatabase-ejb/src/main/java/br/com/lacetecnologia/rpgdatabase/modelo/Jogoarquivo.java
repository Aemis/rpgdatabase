/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leticia Sena
 */
@Entity
@Table(name = "jogoarquivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogoarquivo.findAll", query = "SELECT j FROM Jogoarquivo j"),
    @NamedQuery(name = "Jogoarquivo.findByJogoarquivocodigo", query = "SELECT j FROM Jogoarquivo j WHERE j.jogoarquivocodigo = :jogoarquivocodigo")})
public class Jogoarquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jogoarquivocodigo")
    private Integer jogoarquivocodigo;
    @JoinColumn(name = "jogoarquivoarquivo", referencedColumnName = "arquivocodigo")
    @ManyToOne(optional = false)
    private Arquivo jogoarquivoarquivo;
    @JoinColumn(name = "jogoarquivojogo", referencedColumnName = "jogocodigo")
    @ManyToOne(optional = false)
    private Jogo jogoarquivojogo;

    public Jogoarquivo() {
    }

    public Jogoarquivo(Integer jogoarquivocodigo) {
        this.jogoarquivocodigo = jogoarquivocodigo;
    }

    public Integer getJogoarquivocodigo() {
        return jogoarquivocodigo;
    }

    public void setJogoarquivocodigo(Integer jogoarquivocodigo) {
        this.jogoarquivocodigo = jogoarquivocodigo;
    }

    public Arquivo getJogoarquivoarquivo() {
        return jogoarquivoarquivo;
    }

    public void setJogoarquivoarquivo(Arquivo jogoarquivoarquivo) {
        this.jogoarquivoarquivo = jogoarquivoarquivo;
    }

    public Jogo getJogoarquivojogo() {
        return jogoarquivojogo;
    }

    public void setJogoarquivojogo(Jogo jogoarquivojogo) {
        this.jogoarquivojogo = jogoarquivojogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jogoarquivocodigo != null ? jogoarquivocodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogoarquivo)) {
            return false;
        }
        Jogoarquivo other = (Jogoarquivo) object;
        if ((this.jogoarquivocodigo == null && other.jogoarquivocodigo != null) || (this.jogoarquivocodigo != null && !this.jogoarquivocodigo.equals(other.jogoarquivocodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lacetecnologia.rpgdatabase.modelo.Jogoarquivo[ jogoarquivocodigo=" + jogoarquivocodigo + " ]";
    }
    
}
