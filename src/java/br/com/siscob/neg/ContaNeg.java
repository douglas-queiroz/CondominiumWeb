/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.ContaDAO;
import br.com.siscob.model.Conta;

/**
 *
 * @author Douglas
 */
public class ContaNeg extends GenericNeg<Conta>{

    public ContaNeg() {
        super(new ContaDAO());
    }
    
}
