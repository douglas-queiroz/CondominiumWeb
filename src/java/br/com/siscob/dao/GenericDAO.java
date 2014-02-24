/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.dao;

import br.com.siscob.model.AbstractEntity;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.FabricaConexao;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Classe generica da DAO.
 *
 * @author Douglas
 * @param <T> Entidade
 */
public abstract class GenericDAO<T> {

    /**
     * Referencia da entidade
     */
    private Class<T> entityClass;

    /**
     * Construtor do GenericDAO
     *
     * @param entityClass - Referencia da entidade.
     */
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Método insert
     *
     * @param entity - Objeto T a ser excluido.
     */
    public void save(T entity) {
        EntityManager em = FabricaConexao.obterManager();
        
        em.persist(entity);
    }

    /**
     * Método delete
     *
     * @param id - ID do objeto a ser excluido.
     */
    public void delete(Object id) {
        EntityManager em = FabricaConexao.obterManager();
        
        em.remove(em.getReference(entityClass, ((AbstractEntity) id).getId()));
    }

    /**
     * Método de atualizacao do objeto.
     *
     * @param entity - objeto T a ser atualizar.
     * @return
     */
    public T update(T entity) {
        EntityManager em = FabricaConexao.obterManager();
        
        return em.merge(entity);
    }

    /**
     * Método de consulta por ID.
     */
    public T find(int entityID) {
        EntityManager em = FabricaConexao.obterManager();
        return em.find(entityClass, entityID);
    }

    public List<T> consultar(Usuario usuario) {
        EntityManager em = FabricaConexao.obterManager();
        return ((Session) em.getDelegate())
                .createCriteria(entityClass)
                .add(Restrictions.eq("usuario", usuario))
                .addOrder(Order.asc("descricao"))
                .list();
    }

    public List<T> consultar(Usuario usuario, String ordenarPor) {
        EntityManager em = FabricaConexao.obterManager();
        return ((Session) em.getDelegate())
                .createCriteria(entityClass)
                .add(Restrictions.eq("usuario", usuario))
                .addOrder(Order.desc(ordenarPor))
                .list();
    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> consultar() {
        EntityManager em = FabricaConexao.obterManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return em.createQuery(cq).getResultList();
    }

    /**
     * *
     * Método de consulta que retorna um unico resultado.
     *
     * @param namedQuery - nome da query
     * @param parameters - map de parametro<nome,valor>
     * @return Um unico resultado<T>
     */
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        EntityManager em = FabricaConexao.obterManager();
        
        Query query = em.createNamedQuery(namedQuery);
        if (parameters != null && !parameters.isEmpty()) {
            populateQueryParameters(query, parameters);
        }
        
        return (T) query.getSingleResult();
    }

    /**
     * Método de consulta que retorna uma lista de resultados.
     *
     * @param namedQuery - nome da query
     * @param parameters - map de parametro <nome,valor>
     * @return Lista de objetos<T>
     */
    protected List<T> findResultList(String namedQuery, Map<String, Object> parameters) {
        EntityManager em = FabricaConexao.obterManager();

        Query query = em.createNamedQuery(namedQuery);
        if (parameters != null && !parameters.isEmpty()) {
            populateQueryParameters(query, parameters);
        }

        return (List<T>) query.getResultList();
    }

    /**
     * Método popula os parametros na query.
     *
     * @param query - quere a ser populada
     * @param parameters - parametros da query
     */
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {

        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
