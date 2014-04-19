/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.dao;

import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.FabricaConexao;
import java.io.Serializable;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Classe DAO de usuario
 *
 * @author Douglas Queiroz
 */
public class UsuarioDAO extends GenericDAO<Usuario> implements Serializable {

    private static final long serialVersionUID = -278035410354030361L;

    /**
     * Contrutor
     */
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public List<Usuario> consultar(String nome, String cpf, Condominio condominio) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nome", "%" + nome + "%");
        param.put("cpf", "%" + cpf + "%");

        if (condominio == null) {
            return super.findResultList("Usuario.findByNomeCpf", param);
        } else {
            param.put("condominio", condominio);

            return super.findResultList("Usuario.findByNomeCpfCondominio", param);
        }
    }

    public List<Usuario> consultar(String filtroDefault) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("filtroDefault", "%" + filtroDefault + "%");

        return super.findResultList("Usuario.findByFiltroDefault", param);
    }

    /**
     * Método para obter um unico usuário pelo login
     *
     * @param login - Login
     * @return Usuario
     */
    public Usuario obter(String login) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cpf", login);
        return super.findOneResult("Usuario.findByCpf", param);
    }

    public List<Usuario> consultarUltimosAcessos() {
        EntityManager em = FabricaConexao.obterManager();
        return ((Session) em.getDelegate())
                .createCriteria(Usuario.class)
                .add(Restrictions.isNotNull("ultimoAcesso"))
                .addOrder(Order.desc("ultimoAcesso"))
                .setMaxResults(20).list();
    }
}
