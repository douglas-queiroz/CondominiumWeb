/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.CondominioDAO;
import br.com.siscob.model.Condominio;
import java.io.Serializable;

/**
 *
 * @author Douglas
 */
public class CondominioNeg extends GenericNeg<Condominio> implements Serializable{
    private static final long serialVersionUID = 2487887587744628197L;

    public CondominioNeg() {
        super(new CondominioDAO());
    }
}
