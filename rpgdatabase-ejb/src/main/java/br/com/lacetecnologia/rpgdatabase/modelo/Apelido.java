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
@Table(name = "apelido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apelido.findAll", query = "SELECT a FROM Apelido a"),
    @NamedQuery(name = "Apelido.findByApelidocodigo", query = "SELECT a FROM Apelido a WHERE a.apelidocodigo = :apelidocodigo"),
    @NamedQuery(name = "Apelido.findByApelidonome", query = "SELECT a FROM Apelido a WHERE a.apelidonome = :apelidonome"),
    @NamedQuery(name = "Apelido.findByApelidodatainiciouso", query = "SELECT a FROM Apelido a WHERE a.apelidodatainiciouso = :apelidodatainiciouso")})
public class Apelido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apelidocodigo")
    private Integer apelidocodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apelidonome")
    private String apelidonome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apelidodatainiciouso")
    @Temporal(TemporalType.DATE)
    private Date apelidodatainiciouso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apelido")
    private List<Acao> acaoList;

    public Apelido() {
    }

    public Apelido(Integer apelidocodigo) {
        this.apelidocodigo = apelidocodigo;
    }

    public Apelido(Integer apelidocodigo, String apelidonome, Date apelidodatainiciouso) {
        this.apelidocodigo = apelidocodigo;
        this.apelidonome = apelidonome;
        this.apelidodatainiciouso = apelidodatainiciouso;
    }

    public Integer getApelidocodigo() {
        return apelidocodigo;
    }

    public void setApelidocodigo(Integer apelidocodigo) {
        this.apelidocodigo = apelidocodigo;
    }

    public String getApelidonome() {
        return apelidonome;
    }

    public void setApelidonome(String apelidonome) {
        this.apelidonome = apelidonome;
    }

    public Date getApelidodatainiciouso() {
        return apelidodatainiciouso;
    }

    public void setApelidodatainiciouso(Date apelidodatainiciouso) {
        this.apelidodatainiciouso = apelidodatainiciouso;
    }

    @XmlTransient
    public List<Acao> getAcaoList() {
        return acaoList;
    }

    public void setAcaoList(List<Acao> acaoList) {
        this.acaoList = acaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apelidocodigo != null ? apelidocodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apelido)) {
            return false;
        }
        Apelido other = (Apelido) object;
        if ((this.apelidocodigo == null && other.apelidocodigo != null) || (this.apelidocodigo != null && !this.apelidocodigo.equals(other.apelidocodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lacetecnologia.rpgdatabase.modelo.Apelido[ apelidocodigo=" + apelidocodigo + " ]";
    }
    
}
