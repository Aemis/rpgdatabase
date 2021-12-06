/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.modelo;

import com.google.gson.Gson;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leticia Sena
 */
@Entity
@Table(name = "arquivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivo.buscarTodos", query = "SELECT a FROM Arquivo a"),
    @NamedQuery(name = "Arquivo.buscarPorUsuario", query = "SELECT a FROM Arquivo a JOIN a.usuario u WHERE u.codigo = :codigo ORDER BY a.entradaDataHora DESC"),
    @NamedQuery(name = "Arquivo.buscarPorcodigo", query = "SELECT a FROM Arquivo a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Arquivo.buscarPorNome", query = "SELECT a FROM Arquivo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Arquivo.buscarPorCaminhoOrigem", query = "SELECT a FROM Arquivo a WHERE a.caminhoOrigem = :caminhoOrigem"),
    @NamedQuery(name = "Arquivo.buscarPorCaminhoArmazenamento", query = "SELECT a FROM Arquivo a WHERE a.caminhoArmazenamento = :caminhoArmazenamento"),
    @NamedQuery(name = "Arquivo.buscarPorDataHora", query = "SELECT a FROM Arquivo a WHERE a.dataHora = :dataHora"),
    @NamedQuery(name = "Arquivo.buscarPorTamanho", query = "SELECT a FROM Arquivo a WHERE a.tamanho = :tamanho"),
    @NamedQuery(name = "Arquivo.buscarPorEntradaDatahora", query = "SELECT a FROM Arquivo a WHERE a.entradaDataHora = :entradaDataHora"),
    @NamedQuery(name = "Arquivo.buscarPorProcessamentoDataHora", query = "SELECT a FROM Arquivo a WHERE a.processamentoDataHora = :processamentoDataHora"),
    @NamedQuery(name = "Arquivo.buscarPorGravacaoDataHora", query = "SELECT a FROM Arquivo a WHERE a.processamentoDataHora = :processamentoDataHora"),
    @NamedQuery(name = "Arquivo.buscarPorFinalizacaoDataHora", query = "SELECT a FROM Arquivo a WHERE a.finalizacaoDataHora = :finalizacaoDataHora")})
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "arquivocodigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "arquivonome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "arquivocaminhoorigem")
    private String caminhoOrigem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "arquivocaminhoarmazenamento")
    private String caminhoArmazenamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arquivodatahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arquivotamanho")
    private BigInteger tamanho;
    @JoinColumn(name = "arquivousuario", referencedColumnName = "usuariocodigo")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arquivodatahoraentrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entradaDataHora;
    @Column(name = "arquivodatahoraprocessamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processamentoDataHora;
    @Column(name = "arquivogravacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gravacaoDataHora;
    @Column(name = "arquivofinalizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalizacaoDataHora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogoarquivoarquivo")
    private List<Jogoarquivo> jogos;
    @Column(name = "arquivoqtdlinhas")
    private Integer qtdLinhas;
    @Column(name = "arquivoultlinhalida")
    private Integer ultimaLinhaLida;

    public Arquivo() {
    }

    public Arquivo(Integer codigo) {
        this.codigo = codigo;
    }

    public Arquivo(Integer codigo, String nome, String caminhoOrigem, String caminhoArmazenamento, Date dataHora, BigInteger tamanho, Date entradaDataHora) {
        this.codigo = codigo;
        this.nome = nome;
        this.caminhoOrigem = caminhoOrigem;
        this.caminhoArmazenamento = caminhoArmazenamento;
        this.dataHora = dataHora;
        this.tamanho = tamanho;
        this.entradaDataHora = entradaDataHora;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoOrigem() {
        return caminhoOrigem;
    }

    public void setCaminhoOrigem(String caminhoOrigem) {
        this.caminhoOrigem = caminhoOrigem;
    }

    public String getCaminhoArmazenamento() {
        return caminhoArmazenamento;
    }

    public void setCaminhoArmazenamento(String caminhoArmazenamento) {
        this.caminhoArmazenamento = caminhoArmazenamento;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public BigInteger getTamanho() {
        return tamanho;
    }

    public void setTamanho(BigInteger tamanho) {
        this.tamanho = tamanho;
    }

    public Date getEntradaDataHora() {
        return entradaDataHora;
    }

    public void setEntradaDataHora(Date entradaDataHora) {
        this.entradaDataHora = entradaDataHora;
    }

    public Date getProcessamentoDataHora() {
        return processamentoDataHora;
    }

    public void setProcessamentoDataHora(Date processamentoDataHora) {
        this.processamentoDataHora = processamentoDataHora;
    }

    public Date getGravacaoDataHora() {
        return gravacaoDataHora;
    }

    public void setGravacaoDataHora(Date gravacaoDataHora) {
        this.gravacaoDataHora = gravacaoDataHora;
    }

    public Date getFinalizacaoDataHora() {
        return finalizacaoDataHora;
    }

    public void setFinalizacaoDataHora(Date finalizacaoDataHora) {
        this.finalizacaoDataHora = finalizacaoDataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getQtdLinhas() {
        return qtdLinhas;
    }

    public void setQtdLinhas(Integer qtdLinhas) {
        this.qtdLinhas = qtdLinhas;
    }

    public Integer getUltimaLinhaLida() {
        return ultimaLinhaLida;
    }

    public void setUltimaLinhaLida(Integer ultimaLinhaLida) {
        this.ultimaLinhaLida = ultimaLinhaLida;
    }
    
    

    @XmlTransient
    public List<Jogoarquivo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogoarquivo> jogos) {
        this.jogos = jogos;
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
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}
