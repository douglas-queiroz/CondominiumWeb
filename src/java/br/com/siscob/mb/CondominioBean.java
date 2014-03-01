/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.mb;

import br.com.siscob.model.Condominio;
import br.com.siscob.neg.CondominioNeg;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;

/**
 *
 * @author Douglas
 */
@ManagedBean
@ViewScoped
public class CondominioBean extends GenericBean<Condominio> implements Serializable{
    private static final long serialVersionUID = 7943810166771452995L;

    public CondominioBean() {
        super(new CondominioNeg());
    }
    
    public SelectItem[] getUfs(){
    	SelectItem[] items = new SelectItem[UnidadeFederativa.values().length];
		int i = 0;
		for (UnidadeFederativa t : UnidadeFederativa.values()) {
			items[i++] = new SelectItem(t, t.toString());
		}
		return items;
    }

    @Override
    List<Condominio> carregarLista() {
        return ((CondominioNeg) super.obterNeg()).consultar();
    }

    @Override
    Condominio iniciarObjeto() {
        return new Condominio();
    }
}
