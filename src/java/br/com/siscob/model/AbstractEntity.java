/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe de abstacao de entidades
 *
 * @author Douglas Queiroz
 */
@MappedSuperclass
public abstract class AbstractEntity {
    
    /**
     * ID da entidade
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Integer id = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método verifica se o objeto atual é igual a outro.
     * @param obj - Objeto
     * @return Verdadeiro ou falso
     */
    @Override
    public boolean equals(Object obj) {
        try{
        if(obj != null){
            return this.id == ((AbstractEntity)obj).getId();
        }else{
            return true;
        }
        }catch(ClassCastException e){
            return false;
        }
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
