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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Douglas
 */
@Entity
@Table(name = "grupo", catalog = "siscob_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findById", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
    @NamedQuery(name = "Grupo.findByDescricao", query = "SELECT g FROM Grupo g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Grupo.findBySituacao", query = "SELECT g FROM Grupo g WHERE g.situacao = :situacao")})
public class Grupo extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -2586385644574993223L;
    
    @Column(name = "descricao", length = 45)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "situacao", nullable = false)
    private int situacao;

    public Grupo() {
    }

    public Grupo(Integer id) {
        this.id = id;
    }

    public Grupo(Integer id, int situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "br.com.siscob.model.Grupo[ id=" + id + " ]";
    }
    
}
