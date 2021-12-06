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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leticia Sena
 */
@Entity
@Table(name = "sessao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessao.findAll", query = "SELECT s FROM Sessao s"),
    @NamedQuery(name = "Sessao.findBySessaocodigo", query = "SELECT s FROM Sessao s WHERE s.sessaocodigo = :sessaocodigo"),
    @NamedQuery(name = "Sessao.findBySessaodatahorainicio", query = "SELECT s FROM Sessao s WHERE s.sessaodatahorainicio = :sessaodatahorainicio"),
    @NamedQuery(name = "Sessao.findBySessaodatahorafim", query = "SELECT s FROM Sessao s WHERE s.sessaodatahorafim = :sessaodatahorafim"),
    @NamedQuery(name = "Sessao.findBySessaonumerosessaojogo", query = "SELECT s FROM Sessao s WHERE s.sessaonumerosessaojogo = :sessaonumerosessaojogo")})
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sessaocodigo")
    private Integer sessaocodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sessaodatahorainicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessaodatahorainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sessaodatahorafim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessaodatahorafim;
    @Column(name = "sessaonumerosessaojogo")
    private Integer sessaonumerosessaojogo;
    @JoinColumn(name = "sessaojogo", referencedColumnName = "jogocodigo")
    @ManyToOne(optional = false)
    private Jogo sessaojogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessao")
    private List<Acao> acaoList;

    public Sessao() {
    }

    public Sessao(Integer sessaocodigo) {
        this.sessaocodigo = sessaocodigo;
    }

    public Sessao(Integer sessaocodigo, Date sessaodatahorainicio, Date sessaodatahorafim) {
        this.sessaocodigo = sessaocodigo;
        this.sessaodatahorainicio = sessaodatahorainicio;
        this.sessaodatahorafim = sessaodatahorafim;
    }

    public Integer getSessaocodigo() {
        return sessaocodigo;
    }

    public void setSessaocodigo(Integer sessaocodigo) {
        this.sessaocodigo = sessaocodigo;
    }

    public Date getSessaodatahorainicio() {
        return sessaodatahorainicio;
    }

    public void setSessaodatahorainicio(Date sessaodatahorainicio) {
        this.sessaodatahorainicio = sessaodatahorainicio;
    }

    public Date getSessaodatahorafim() {
        return sessaodatahorafim;
    }

    public void setSessaodatahorafim(Date sessaodatahorafim) {
        this.sessaodatahorafim = sessaodatahorafim;
    }

    public Integer getSessaonumerosessaojogo() {
        return sessaonumerosessaojogo;
    }

    public void setSessaonumerosessaojogo(Integer sessaonumerosessaojogo) {
        this.sessaonumerosessaojogo = sessaonumerosessaojogo;
    }

    public Jogo getSessaojogo() {
        return sessaojogo;
    }

    public void setSessaojogo(Jogo sessaojogo) {
        this.sessaojogo = sessaojogo;
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
        hash += (sessaocodigo != null ? sessaocodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessao)) {
            return false;
        }
        Sessao other = (Sessao) object;
        if ((this.sessaocodigo == null && other.sessaocodigo != null) || (this.sessaocodigo != null && !this.sessaocodigo.equals(other.sessaocodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lacetecnologia.rpgdatabase.modelo.Sessao[ sessaocodigo=" + sessaocodigo + " ]";
    }
    
}
