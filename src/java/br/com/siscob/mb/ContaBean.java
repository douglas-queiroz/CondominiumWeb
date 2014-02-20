package br.com.siscob.mb;

import br.com.siscob.model.Conta;
import br.com.siscob.neg.ContaNeg;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.jrimum.bopepo.BancosSuportados;

@ManagedBean
@ViewScoped
public class ContaBean extends GenericBean<Conta> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ContaBean() {
        super(new ContaNeg());
    }

    public SelectItem[] getBanco() {
        SelectItem[] items = new SelectItem[BancosSuportados.values().length];
        int i = 0;
        for (BancosSuportados t : BancosSuportados.values()) {
            items[i++] = new SelectItem(t, t.toString());
        }
        return items;
    }

    @Override
    Conta iniciarObjeto() {
        return new Conta();
    }

    @Override
    List<Conta> carregarLista() {
        return super.obterNeg().consultar();
    }

}
