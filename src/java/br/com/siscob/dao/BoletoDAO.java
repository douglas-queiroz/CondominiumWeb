/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.dao;

import br.com.siscob.model.Boleto;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.FabricaConexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Douglas
 */
public class BoletoDAO extends GenericDAO<Boleto> implements Serializable{
    private static final long serialVersionUID = 2464574925238792909L;

    public BoletoDAO() {
        super(Boleto.class);
    }
    
    public List<Boleto> consultar(Usuario usuario) {
        EntityManager em = FabricaConexao.obterManager();
        return ((Session) em.getDelegate())
                .createCriteria(Boleto.class)
                .add(Restrictions.eq("usuario", usuario))
                .addOrder(Order.asc("dataVencimento"))
                .list();
    }
}
