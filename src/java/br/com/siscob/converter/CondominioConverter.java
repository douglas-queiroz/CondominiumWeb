/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.converter;

import br.com.siscob.model.Condominio;
import br.com.siscob.neg.CondominioNeg;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Douglas
 */
@FacesConverter(value = "CondominioConverter", forClass = Condominio.class)
public class CondominioConverter implements Converter {
    private CondominioNeg neg = new CondominioNeg();

    @Override
    public Condominio getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return neg.obter(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Condominio) {
            return String.valueOf(((Condominio) value).getId());
        } else {
            return null;
        }
    }
}
