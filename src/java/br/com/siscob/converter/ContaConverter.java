/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.converter;

import br.com.siscob.model.Conta;
import br.com.siscob.neg.ContaNeg;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Douglas
 */
@FacesConverter(value = "ContaConverter", forClass = Conta.class)
public class ContaConverter implements Converter {
    private ContaNeg neg = new ContaNeg();

    @Override
    public Conta getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return neg.obter(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Conta) {
            return String.valueOf(((Conta) value).getId());
        } else {
            return null;
        }
    }
}
