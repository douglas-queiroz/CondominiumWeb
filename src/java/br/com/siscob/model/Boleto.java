/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "boleto", catalog = "siscob_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findById", query = "SELECT b FROM Boleto b WHERE b.id = :id"),
    @NamedQuery(name = "Boleto.findByCodigoOperacao", query = "SELECT b FROM Boleto b WHERE b.codigoOperacao = :codigoOperacao"),
    @NamedQuery(name = "Boleto.findByNumeroDocumento", query = "SELECT b FROM Boleto b WHERE b.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Boleto.findByNossoNumero", query = "SELECT b FROM Boleto b WHERE b.nossoNumero = :nossoNumero"),
    @NamedQuery(name = "Boleto.findByDigitoNossoNumero", query = "SELECT b FROM Boleto b WHERE b.digitoNossoNumero = :digitoNossoNumero"),
    @NamedQuery(name = "Boleto.findByValor", query = "SELECT b FROM Boleto b WHERE b.valor = :valor"),
    @NamedQuery(name = "Boleto.findByDataDocumento", query = "SELECT b FROM Boleto b WHERE b.dataDocumento = :dataDocumento"),
    @NamedQuery(name = "Boleto.findByDataVencimento", query = "SELECT b FROM Boleto b WHERE b.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "Boleto.findByTipoTitulo", query = "SELECT b FROM Boleto b WHERE b.tipoTitulo = :tipoTitulo"),
    @NamedQuery(name = "Boleto.findByAceite", query = "SELECT b FROM Boleto b WHERE b.aceite = :aceite"),
    @NamedQuery(name = "Boleto.findByDesconto", query = "SELECT b FROM Boleto b WHERE b.desconto = :desconto"),
    @NamedQuery(name = "Boleto.findByDeducao", query = "SELECT b FROM Boleto b WHERE b.deducao = :deducao"),
    @NamedQuery(name = "Boleto.findByMora", query = "SELECT b FROM Boleto b WHERE b.mora = :mora"),
    @NamedQuery(name = "Boleto.findByAcrescimo", query = "SELECT b FROM Boleto b WHERE b.acrescimo = :acrescimo"),
    @NamedQuery(name = "Boleto.findByValorCobrado", query = "SELECT b FROM Boleto b WHERE b.valorCobrado = :valorCobrado"),
    @NamedQuery(name = "Boleto.findByLocalPagamento", query = "SELECT b FROM Boleto b WHERE b.localPagamento = :localPagamento"),
    @NamedQuery(name = "Boleto.findByInstrucaoPagamento", query = "SELECT b FROM Boleto b WHERE b.instrucaoPagamento = :instrucaoPagamento")})
public class Boleto extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 3088501999986467826L;
    
    @Column(name = "codigo_operacao", nullable = false)
    private int codigoOperacao;
    @Basic(optional = false)
    @Column(name = "numero_documento", nullable = false, length = 200)
    private String numeroDocumento;
    @Basic(optional = false)
    @Column(name = "nosso_numero", nullable = false, length = 100)
    private String nossoNumero;
    @Basic(optional = false)
    @Column(name = "digito_nosso_numero", nullable = false, length = 10)
    private String digitoNossoNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "data_documento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDocumento;
    @Basic(optional = false)
    @Column(name = "data_vencimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    @Basic(optional = false)
    @Column(name = "tipo_titulo", nullable = false)
    private int tipoTitulo;
    @Basic(optional = false)
    @Column(name = "aceite", nullable = false)
    private int aceite;
    @Basic(optional = false)
    @Column(name = "desconto", nullable = false, precision = 19, scale = 2)
    private BigDecimal desconto;
    @Basic(optional = false)
    @Column(name = "deducao", nullable = false, precision = 19, scale = 2)
    private BigDecimal deducao;
    @Basic(optional = false)
    @Column(name = "mora", nullable = false, precision = 19, scale = 2)
    private BigDecimal mora;
    @Basic(optional = false)
    @Column(name = "acrescimo", nullable = false, precision = 19, scale = 2)
    private BigDecimal acrescimo;
    @Basic(optional = false)
    @Column(name = "valor_cobrado", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorCobrado;
    @Column(name = "local_pagamento", length = 250)
    private String localPagamento;
    @Column(name = "instrucao_pagamento", length = 250)
    private String instrucaoPagamento;
    @JoinColumn(name = "condominio_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Condominio condominioId;
    @JoinColumn(name = "conta_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conta contaId;

    public Boleto() {
    }

    public Boleto(Integer id, int codigoOperacao, String numeroDocumento, String nossoNumero, String digitoNossoNumero, BigDecimal valor, Date dataDocumento, Date dataVencimento, int tipoTitulo, int aceite, BigDecimal desconto, BigDecimal deducao, BigDecimal mora, BigDecimal acrescimo, BigDecimal valorCobrado) {
        super.id = id;
        this.codigoOperacao = codigoOperacao;
        this.numeroDocumento = numeroDocumento;
        this.nossoNumero = nossoNumero;
        this.digitoNossoNumero = digitoNossoNumero;
        this.valor = valor;
        this.dataDocumento = dataDocumento;
        this.dataVencimento = dataVencimento;
        this.tipoTitulo = tipoTitulo;
        this.aceite = aceite;
        this.desconto = desconto;
        this.deducao = deducao;
        this.mora = mora;
        this.acrescimo = acrescimo;
        this.valorCobrado = valorCobrado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoOperacao() {
        return codigoOperacao;
    }

    public void setCodigoOperacao(int codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getDigitoNossoNumero() {
        return digitoNossoNumero;
    }

    public void setDigitoNossoNumero(String digitoNossoNumero) {
        this.digitoNossoNumero = digitoNossoNumero;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getTipoTitulo() {
        return tipoTitulo;
    }

    public void setTipoTitulo(int tipoTitulo) {
        this.tipoTitulo = tipoTitulo;
    }

    public int getAceite() {
        return aceite;
    }

    public void setAceite(int aceite) {
        this.aceite = aceite;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getDeducao() {
        return deducao;
    }

    public void setDeducao(BigDecimal deducao) {
        this.deducao = deducao;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(BigDecimal acrescimo) {
        this.acrescimo = acrescimo;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getInstrucaoPagamento() {
        return instrucaoPagamento;
    }

    public void setInstrucaoPagamento(String instrucaoPagamento) {
        this.instrucaoPagamento = instrucaoPagamento;
    }

    public Condominio getCondominioId() {
        return condominioId;
    }

    public void setCondominioId(Condominio condominioId) {
        this.condominioId = condominioId;
    }

    public Conta getContaId() {
        return contaId;
    }

    public void setContaId(Conta contaId) {
        this.contaId = contaId;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Boleto[ id=" + id + " ]";
    }
    
}
