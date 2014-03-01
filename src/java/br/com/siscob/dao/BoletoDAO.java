/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.dao;

import br.com.siscob.model.Boleto;
import java.io.Serializable;

/**
 *
 * @author Douglas
 */
public class BoletoDAO extends GenericDAO<Boleto> implements Serializable{
    private static final long serialVersionUID = 2464574925238792909L;

    public BoletoDAO() {
        super(Boleto.class);
    }
}
