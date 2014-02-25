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
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "endereco", catalog = "siscob_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id"),
    @NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade = :cidade"),
    @NamedQuery(name = "Endereco.findByUf", query = "SELECT e FROM Endereco e WHERE e.uf = :uf"),
    @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep"),
    @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE e.logradouro = :logradouro"),
    @NamedQuery(name = "Endereco.findByNumero", query = "SELECT e FROM Endereco e WHERE e.numero = :numero")})
public class Endereco extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 5944888391993171403L;
    
    @Basic(optional = false)
    @Column(name = "cidade", nullable = false, length = 250)
    private String cidade;
    @Basic(optional = false)
    @Column(name = "uf", nullable = false, length = 2)
    private UnidadeFederativa uf;
    @Basic(optional = false)
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;
    @Basic(optional = false)
    @Column(name = "bairro", nullable = false, length = 250)
    private String bairro;
    @Basic(optional = false)
    @Column(name = "logradouro", nullable = false, length = 250)
    private String logradouro;
    @Basic(optional = false)
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endereco", fetch = FetchType.LAZY)
    private List<Condominio> condominioList;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, String cidade, UnidadeFederativa uf, String cep, String bairro, String logradouro, String numero) {
        this.id = id;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @XmlTransient
    public List<Condominio> getCondominioList() {
        return condominioList;
    }

    public void setCondominioList(List<Condominio> condominioList) {
        this.condominioList = condominioList;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Endereco[ id=" + id + " ]";
    }
    
}
