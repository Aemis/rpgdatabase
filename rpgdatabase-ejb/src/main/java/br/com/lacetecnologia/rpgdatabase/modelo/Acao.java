/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.modelo;

import br.com.lacetecnologia.rpgdatabase.enumerados.TipoAcao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leticia Sena
 */
@Entity
@Table(name = "acao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acao.buscarTodos", query = "SELECT a FROM Acao a"),
    @NamedQuery(name = "Acao.buscarPorCodigo", query = "SELECT a FROM Acao a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Acao.buscarPorDataHora", query = "SELECT a FROM Acao a WHERE a.dataHora = :dataHora"),
    @NamedQuery(name = "Acao.buscarPorTipoAcao", query = "SELECT a FROM Acao a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Acao.buscarPorDescricao", query = "SELECT a FROM Acao a WHERE a.descricao = :descricao")})
public class Acao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acaocodigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acaodatahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acaotipo")
    @Enumerated(EnumType.STRING)
    private TipoAcao tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "acaodescricao")
    private String descricao;
    @JoinColumn(name = "acaoapelido", referencedColumnName = "apelidocodigo")
    @ManyToOne(optional = false)
    private Apelido apelido;
    @JoinColumn(name = "acaosessao", referencedColumnName = "sessaocodigo")
    @ManyToOne(optional = false)
    private Sessao sessao;

    public Acao() {
    }

    public Acao(Integer acaocodigo) {
        this.codigo = acaocodigo;
    }

    public Acao(Integer acaocodigo, Date acaodatahora, TipoAcao acaotipo, String acaodescricao) {
        this.codigo = acaocodigo;
        this.dataHora = acaodatahora;
        this.tipo = acaotipo;
        this.descricao = acaodescricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAcao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Apelido getApelido() {
        return apelido;
    }

    public void setApelido(Apelido apelido) {
        this.apelido = apelido;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acao)) {
            return false;
        }
        Acao other = (Acao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lacetecnologia.rpgdatabase.modelo.Acao[ acaocodigo=" + codigo + " ]";
    }
    
}
