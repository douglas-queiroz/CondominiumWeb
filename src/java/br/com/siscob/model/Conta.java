/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.jrimum.bopepo.BancosSuportados;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findById", query = "SELECT c FROM Conta c WHERE c.id = :id"),
    @NamedQuery(name = "Conta.findByBanco", query = "SELECT c FROM Conta c WHERE c.banco = :banco"),
    @NamedQuery(name = "Conta.findByConta", query = "SELECT c FROM Conta c WHERE c.conta = :conta"),
    @NamedQuery(name = "Conta.findByDigitoCc", query = "SELECT c FROM Conta c WHERE c.digitoCc = :digitoCc"),
    @NamedQuery(name = "Conta.findByAgencia", query = "SELECT c FROM Conta c WHERE c.agencia = :agencia"),
    @NamedQuery(name = "Conta.findByDigitoAg", query = "SELECT c FROM Conta c WHERE c.digitoAg = :digitoAg"),
    @NamedQuery(name = "Conta.findByCarteira", query = "SELECT c FROM Conta c WHERE c.carteira = :carteira")})
public class Conta extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 843876932328973184L;
    
    @Basic(optional = false)
    @Column(name = "banco", nullable = false)
    private BancosSuportados banco;
    @Basic(optional = false)
    @Column(name = "conta", nullable = false)
    private int conta;
    @Basic(optional = false)
    @Column(name = "digito_cc", nullable = false, length = 2)
    private String digitoCc;
    @Basic(optional = false)
    @Column(name = "agencia", nullable = false)
    private int agencia;
    @Basic(optional = false)
    @Column(name = "digito_ag", nullable = false, length = 2)
    private String digitoAg;
    @Basic(optional = false)
    @Column(name = "carteira", nullable = false)
    private int carteira;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaId", fetch = FetchType.LAZY)
    private List<Boleto> boletoList;
    @Column(name = "codigo_operacao")
    private String codigoOperacao;

    public Conta() {
    }

    public Conta(Integer id) {
        this.id = id;
    }

    public Conta(Integer id, BancosSuportados banco, int conta, String digitoCc, int agencia, String digitoAg, int carteira) {
        this.id = id;
        this.banco = banco;
        this.conta = conta;
        this.digitoCc = digitoCc;
        this.agencia = agencia;
        this.digitoAg = digitoAg;
        this.carteira = carteira;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BancosSuportados getBanco() {
        return banco;
    }

    public void setBanco(BancosSuportados banco) {
        this.banco = banco;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getDigitoCc() {
        return digitoCc;
    }

    public void setDigitoCc(String digitoCc) {
        this.digitoCc = digitoCc;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getDigitoAg() {
        return digitoAg;
    }

    public void setDigitoAg(String digitoAg) {
        this.digitoAg = digitoAg;
    }

    public int getCarteira() {
        return carteira;
    }

    public void setCarteira(int carteira) {
        this.carteira = carteira;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    public String getCodigoOperacao() {
        return codigoOperacao;
    }

    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Conta[ id=" + id + " ]";
    }
    
}
