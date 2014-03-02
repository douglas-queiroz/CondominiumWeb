/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.BoletoDAO;
import br.com.siscob.model.Boleto;
import br.com.siscob.model.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class BoletoNeg extends GenericNeg<Boleto>{

    public BoletoNeg() {
        super(new BoletoDAO());
    }
    
    public List<Boleto> consultar(Usuario usuario){
        return ((BoletoDAO) super.obterDAO()).consultar(usuario);
    }

    @Override
    public void salvar(Boleto objeto) throws Exception {
        this.gerarNossoNumero(objeto);
        super.salvar(objeto);
    }

    private void gerarNossoNumero(Boleto objeto) {
        Date hoje  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmm");
        String nossoNumero = sdf.format(hoje);
        
        Integer idUsuario = objeto.getUsuario().getId();
        nossoNumero += (idUsuario.toString().length() < 2)? 
                "0"+idUsuario : 
                idUsuario.toString().substring(idUsuario.toString().length() - 2, idUsuario.toString().length());
        
        objeto.setNossoNumero(nossoNumero);
    }
}
