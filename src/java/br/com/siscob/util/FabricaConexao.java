/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dqsl
 */
public class FabricaConexao {

    /**
     * Unidade de pesistencia do projeto.
     */
    private final static String UNIT_NAME = "SisbocPU";
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager obterManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        }

        if (em == null) 
            em = emf.createEntityManager();
        
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
        

        return em;
    }
}
