/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.ContaDAO;
import br.com.siscob.model.Conta;
import java.io.Serializable;

/**
 *
 * @author Douglas
 */
public class ContaNeg extends GenericNeg<Conta> implements Serializable{
    private static final long serialVersionUID = 558869009312555752L;

    public ContaNeg() {
        super(new ContaDAO());
    }
    
}
