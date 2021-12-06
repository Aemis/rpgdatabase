/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leticia Sena
 */
@Entity
@Table(name = "jogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogo.findAll", query = "SELECT j FROM Jogo j"),
    @NamedQuery(name = "Jogo.findByJogocodigo", query = "SELECT j FROM Jogo j WHERE j.jogocodigo = :jogocodigo"),
    @NamedQuery(name = "Jogo.findByJogonome", query = "SELECT j FROM Jogo j WHERE j.jogonome = :jogonome"),
    @NamedQuery(name = "Jogo.findByJogodatahorainicio", query = "SELECT j FROM Jogo j WHERE j.jogodatahorainicio = :jogodatahorainicio"),
    @NamedQuery(name = "Jogo.findByJogodatahoraultimoenvio", query = "SELECT j FROM Jogo j WHERE j.jogodatahoraultimoenvio = :jogodatahoraultimoenvio"),
    @NamedQuery(name = "Jogo.findByJogoestafinalizado", query = "SELECT j FROM Jogo j WHERE j.jogoestafinalizado = :jogoestafinalizado")})
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jogocodigo")
    private Integer jogocodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "jogonome")
    private String jogonome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jogodatahorainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jogodatahorainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jogodatahoraultimoenvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jogodatahoraultimoenvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jogoestafinalizado")
    private boolean jogoestafinalizado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessaojogo")
    private List<Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogoarquivojogo")
    private List<Jogoarquivo> jogoarquivoList;

    public Jogo() {
    }

    public Jogo(Integer jogocodigo) {
        this.jogocodigo = jogocodigo;
    }

    public Jogo(Integer jogocodigo, String jogonome, Date jogodatahorainicio, Date jogodatahoraultimoenvio, boolean jogoestafinalizado) {
        this.jogocodigo = jogocodigo;
        this.jogonome = jogonome;
        this.jogodatahorainicio = jogodatahorainicio;
        this.jogodatahoraultimoenvio = jogodatahoraultimoenvio;
        this.jogoestafinalizado = jogoestafinalizado;
    }

    public Integer getJogocodigo() {
        return jogocodigo;
    }

    public void setJogocodigo(Integer jogocodigo) {
        this.jogocodigo = jogocodigo;
    }

    public String getJogonome() {
        return jogonome;
    }

    public void setJogonome(String jogonome) {
        this.jogonome = jogonome;
    }

    public Date getJogodatahorainicio() {
        return jogodatahorainicio;
    }

    public void setJogodatahorainicio(Date jogodatahorainicio) {
        this.jogodatahorainicio = jogodatahorainicio;
    }

    public Date getJogodatahoraultimoenvio() {
        return jogodatahoraultimoenvio;
    }

    public void setJogodatahoraultimoenvio(Date jogodatahoraultimoenvio) {
        this.jogodatahoraultimoenvio = jogodatahoraultimoenvio;
    }

    public boolean getJogoestafinalizado() {
        return jogoestafinalizado;
    }

    public void setJogoestafinalizado(boolean jogoestafinalizado) {
        this.jogoestafinalizado = jogoestafinalizado;
    }

    @XmlTransient
    public List<Sessao> getSessaoList() {
        return sessaoList;
    }

    public void setSessaoList(List<Sessao> sessaoList) {
        this.sessaoList = sessaoList;
    }

    @XmlTransient
    public List<Jogoarquivo> getJogoarquivoList() {
        return jogoarquivoList;
    }

    public void setJogoarquivoList(List<Jogoarquivo> jogoarquivoList) {
        this.jogoarquivoList = jogoarquivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jogocodigo != null ? jogocodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogo)) {
            return false;
        }
        Jogo other = (Jogo) object;
        if ((this.jogocodigo == null && other.jogocodigo != null) || (this.jogocodigo != null && !this.jogocodigo.equals(other.jogocodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lacetecnologia.rpgdatabase.modelo.Jogo[ jogocodigo=" + jogocodigo + " ]";
    }
    
}
