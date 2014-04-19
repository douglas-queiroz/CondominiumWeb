/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.mb;

import br.com.siscob.model.Boleto;
import br.com.siscob.model.Condominio;
import br.com.siscob.model.Conta;
import br.com.siscob.model.Usuario;
import br.com.siscob.neg.BoletoNeg;
import br.com.siscob.neg.CondominioNeg;
import br.com.siscob.neg.ContaNeg;
import br.com.siscob.util.FacesUtil;
import br.com.tronic.exception.ValidacaoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Douglas
 */
@ManagedBean
@ViewScoped
public class BoletoBean extends GenericBean<Boleto> implements Serializable {

    private static final long serialVersionUID = 3710391879720577473L;

    private List<Boleto> boletosGerados = new ArrayList<Boleto>();
    private List<Conta> contas;
    private List<Condominio> condominios;
    private final CondominioNeg condominioNeg = new CondominioNeg();
    private final ContaNeg contaNeg = new ContaNeg();

    public BoletoBean() {
        super(new BoletoNeg());
    }

    private void validarBoleto(Boleto objeto) throws ValidacaoException {
        List<String> msg = new ArrayList<String>();

        if (objeto.getDataDocumento() == null) {
            msg.add("O campo data documento deve ser preenchido!");
        }

        if (objeto.getDataVencimento() == null) {
            msg.add("O campo data vencimento deve ser preenchido!");
        }

        if (objeto.getValor() <= 0) {
            msg.add("O campo valor deve ser maior que zero!");
        }

        if (!msg.isEmpty()) {
            throw new ValidacaoException(msg);
        }
    }

    public void gerarBoletos() {
        RequestContext context = RequestContext.getCurrentInstance();
        boletosGerados.clear();
        try {
            validarBoleto(super.getObjeto());

            for (Usuario usuario : super.getObjeto().getCondominioId().getUsuarioList()) {
                Boleto boleto = super.getObjeto().clone();
                boleto.setUsuario(usuario);

                boletosGerados.add(boleto);
            }

            this.salvar();

            context.addCallbackParam("valido", true);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BoletoBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addCallbackParam("valido", false);
        } catch (ValidacaoException ex) {
            for (String mensagem : ex.getMensagens()) {
                FacesUtil.exibirMensagemAlerta("Atenção", mensagem);
            }

            context.addCallbackParam("valido", false);
            Logger.getLogger(BoletoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Boleto> getBoletosGerados() {
        return boletosGerados;
    }

    public void setBoletosGerados(List<Boleto> boletosGerados) {
        this.boletosGerados = boletosGerados;
    }

    public List<Condominio> getCondominios() {
        if (condominios == null) {
            condominios = condominioNeg.consultar();
        }

        return condominios;
    }

    public void setCondominios(List<Condominio> condominios) {
        this.condominios = condominios;
    }

    public List<Conta> getContas() {
        if (contas == null) {
            contas = contaNeg.consultar();
        }

        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public SelectItem[] getTiposTitulo() {
        SelectItem[] items = new SelectItem[TipoDeTitulo.values().length];
        int i = 0;
        for (TipoDeTitulo t : TipoDeTitulo.values()) {
            items[i++] = new SelectItem(t, t.toString());
        }
        return items;
    }

    @Override
    Boleto iniciarObjeto() {
        return new Boleto();
    }

    @Override
    List<Boleto> carregarLista() {
        return super.obterNeg().consultar();
    }

    @Override
    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();

        try {
            for (Boleto boleto : boletosGerados) {
                ((BoletoNeg) this.obterNeg()).salvar(boleto);
            }
            
            super.setListaObjetos(this.carregarLista());
            
            FacesUtil.exibirMensagemSucesso("Sucesso", "Boletos salvos com sucesso!");
            context.addCallbackParam("valido", true);
        } catch (ValidacaoException e) {
            for (String mensagem : e.getMensagens()) {
                FacesUtil.exibirMensagemAlerta("Atenção", mensagem);
            }
            Logger.getLogger(BoletoBean.class.getName()).log(Level.SEVERE, null, e);
            context.addCallbackParam("valido", false);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtil.exibirMensagemErro("Erro", "Ocorreu um erro ao salvar!");
            context.addCallbackParam("valido", false);
            Logger.getLogger(BoletoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void baixarBoleto() {
        try {
            ((BoletoNeg) super.obterNeg()).baixarBoleto((Boleto) super.getObjeto());
            FacesUtil.exibirMensagemSucesso("Sucesso", "Boleto baixado com sucesso!");
        } catch (Exception e) {
            Logger.getLogger(Boleto.class.getName()).log(Level.SEVERE, null, e);
            FacesUtil.exibirMensagemErro("Erro", "Ocorreu um erro ao baixar o boleto!");
        }
    }

}
