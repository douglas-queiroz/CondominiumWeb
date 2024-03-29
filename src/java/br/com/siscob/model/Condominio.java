/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "condominio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Condominio.findAll", query = "SELECT c FROM Condominio c"),
    @NamedQuery(name = "Condominio.findById", query = "SELECT c FROM Condominio c WHERE c.id = :id"),
    @NamedQuery(name = "Condominio.findByNome", query = "SELECT c FROM Condominio c WHERE c.nome = :nome"),
    @NamedQuery(name = "Condominio.findByCnpj", query = "SELECT c FROM Condominio c WHERE c.cnpj = :cnpj")})
public class Condominio extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -5137788419356117813L;
    
    @Column(name = "nome", length = 250)
    private String nome;
    @Column(name = "cnpj", length = 45)
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condominioId", fetch = FetchType.LAZY)
    private List<Boleto> boletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condominio", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco endereco;
    @Column(name = "responsabilidade_cedente")
    private boolean responsabilidadeCedente;

    public Condominio() {
    }

    public Condominio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Endereco getEndereco() {
        if(endereco == null){
            endereco = new Endereco();
        }
        
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isResponsabilidadeCedente() {
        return responsabilidadeCedente;
    }

    public void setResponsabilidadeCedente(boolean responsabilidadeCedente) {
        this.responsabilidadeCedente = responsabilidadeCedente;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Condominio[ id=" + id + " ]";
    }
    
}
