/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.BoletoDAO;
import br.com.siscob.model.Boleto;
import br.com.tronic.exception.ValidacaoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class BoletoNeg extends GenericNeg<Boleto>{

    public BoletoNeg() {
        super(new BoletoDAO());
    }

    @Override
    public void salvar(Boleto objeto) throws Exception {
        validarBoleto(objeto);
        
        super.salvar(objeto);
    }

    private void validarBoleto(Boleto objeto) throws ValidacaoException {
        List<String> msgs = new ArrayList<String>();
        
        if(objeto.getNossoNumero().equals(""))
            msgs.add("O nosso número do boleto do cliente "+ objeto.getUsuario().getNome() +" deve ser preenchido");
        
        if(objeto.getDigitoNossoNumero().equals(""))
            msgs.add("O dígito do nosso número do boleto do cliente "+ objeto.getUsuario().getNome() +" deve ser preenchido");
        
        if(!msgs.isEmpty())
            throw new ValidacaoException(msgs);
    }
}
