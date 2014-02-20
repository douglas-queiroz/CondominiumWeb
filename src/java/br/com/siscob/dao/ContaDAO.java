/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.dao;

import br.com.siscob.model.Conta;

/**
 *
 * @author Douglas
 */
public class ContaDAO extends GenericDAO<Conta>{

    public ContaDAO() {
        super(Conta.class);
    }
    
}
