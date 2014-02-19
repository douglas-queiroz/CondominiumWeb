/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.neg;

import br.com.siscob.dao.GenericDAO;
import br.com.siscob.model.AbstractEntity;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.FabricaConexao;
import java.util.List;
import javax.persistence.EntityManager;
import org.eclipse.persistence.exceptions.ValidationException;

/**
 *
 * @author Douglas
 * @param <T>
 */
public abstract class GenericNeg<T> {

    private GenericDAO<T> dao;

    public GenericNeg(GenericDAO<T> dao) {
        this.dao = dao;
    }

    public void salvar(T objeto) throws Exception {
        EntityManager em = FabricaConexao.obterManager();

        try {
            if (((AbstractEntity) objeto).getId() == 0) {
                this.obterDAO().save(objeto, em);
            } else {
                this.obterDAO().update(objeto, em);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
        }
    }

    public void excluir(T objeto) throws ValidationException {
        EntityManager em = FabricaConexao.obterManager();

        try {
            this.obterDAO().delete(objeto, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
        }
    }

    public T obter(long id) {
        return this.obterDAO().find(id);
    }

    public List<T> consultar(Usuario usuario) {
        return this.obterDAO().consultar(usuario);
    }

    public List<T> consultar() {
        return this.obterDAO().consultar();
    }

    public boolean existeDependencia(T objeto) {
        return false;
    }

    protected GenericDAO<T> obterDAO() {
        return dao;
    }
}
