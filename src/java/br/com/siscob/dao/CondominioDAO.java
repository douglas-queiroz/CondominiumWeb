/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.dao;

import br.com.siscob.model.Condominio;
import java.io.Serializable;

/**
 *
 * @author Douglas
 */
public class CondominioDAO extends GenericDAO<Condominio> implements Serializable{
    private static final long serialVersionUID = 8157578541153630281L;

    public CondominioDAO() {
        super(Condominio.class);
    }
    
}
