/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "usuario", catalog = "siscob_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", 
                query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", 
                query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNome", 
                query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByCpf", 
                query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
    @NamedQuery(name = "Usuario.findBySenha", 
                query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByNomeCpf", 
                query = "SELECT u FROM Usuario u WHERE u.nome like :nome and u.cpf like :cpf"),
    @NamedQuery(name = "Usuario.findByFiltroDefault", 
                query = "SELECT u FROM Usuario u WHERE u.nome like :filtroDefault or u.cpf like :filtroDefault"),
    @NamedQuery(name = "Usuario.findByNomeCpfCondominio", 
                query = "SELECT u FROM Usuario u WHERE u.nome like :nome and u.cpf like :cpf and u.condominio =:condominio"),
    @NamedQuery(name = "Usuario.findByPermissao", 
                query = "SELECT u FROM Usuario u WHERE u.permissao = :permissao")})
public class Usuario extends AbstractEntity implements Serializable {
    
    @Column(name = "nome", length = 200)
    private String nome;
    @Column(name = "cpf", length = 50)
    private String cpf;
    @Column(name = "senha", length = 40)
    private String senha;
    @Basic(optional = false)
    @Column(name = "permissao", nullable = false, length = 45)
    private String permissao;
    @JoinColumn(name = "condominio_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Condominio condominio;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String permissao) {
        this.id = id;
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Condominio getCondominio() {
        return condominio;
    }
    
    public void setCondominio(Integer idCondominio) {        
        this.condominio = new Condominio(idCondominio);
    }

    public void setCondominio(Condominio condominioId) {
        this.condominio = condominioId;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Usuario[ id=" + id + " ]";
    }
    
}
