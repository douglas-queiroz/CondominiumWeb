/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.neg;

import br.com.siscob.dao.BoletoDAO;
import br.com.siscob.model.Boleto;
import br.com.siscob.model.Conta;
import br.com.siscob.model.StatusBoleto;
import br.com.siscob.model.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class BoletoNeg extends GenericNeg<Boleto> {

    public BoletoNeg() {
        super(new BoletoDAO());
    }

    public List<Boleto> consultar(Usuario usuario) {
        List<Boleto> boletos = ((BoletoDAO) super.obterDAO()).consultar(usuario);
        for (Boleto boleto : boletos) {
            Conta conta = new ContaNeg().obter(boleto.getContaId().getId());
            boleto.setContaId(conta);
        }
        
        return boletos;
    }

    @Override
    public void salvar(Boleto objeto) throws Exception {
        this.gerarNossoNumero(objeto);
        super.salvar(objeto);
    }

    private void gerarNossoNumero(Boleto objeto) {
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmm");
        String nossoNumero = sdf.format(hoje);
        int idUsuario = objeto.getUsuario().getId();
        String idUsuarioComZero;
        for (idUsuarioComZero = objeto.getUsuario().getId().toString(); idUsuarioComZero.length() < 7; idUsuarioComZero = (new StringBuilder()).append(0).append(idUsuarioComZero).toString());
        nossoNumero = (new StringBuilder()).append(nossoNumero).append(idUsuarioComZero).toString();
        objeto.setNossoNumero(nossoNumero);
    }

    public void baixarBoleto(Boleto boleto) throws Exception {
        boleto.setStatus(StatusBoleto.Baixado);
        super.salvar(boleto);
    }
}
