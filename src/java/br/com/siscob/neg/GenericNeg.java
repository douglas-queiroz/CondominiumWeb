/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.neg;

import br.com.siscob.dao.GenericDAO;
import br.com.siscob.model.AbstractEntity;
import br.com.siscob.model.Usuario;
import java.util.List;
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
        if (((AbstractEntity) objeto).getId() == 0) {
            this.obterDAO().save(objeto);
        } else {
            this.obterDAO().update(objeto);
        }
    }

    public void excluir(T objeto) throws ValidationException {
        this.obterDAO().delete(objeto);
    }

    public T obter(int id) {
        return this.obterDAO().find(id);
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
