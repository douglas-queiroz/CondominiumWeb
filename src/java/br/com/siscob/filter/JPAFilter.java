/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.filter;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Douglas
 */
@WebFilter(servletNames = "Faces Servlet")
public class JPAFilter implements Filter {

    private final static String UNIT_NAME = "SisbocPU";
    private static EntityManagerFactory factory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.factory = Persistence.createEntityManagerFactory(UNIT_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager manager = factory.createEntityManager();
        
        try {
            manager.getTransaction().begin();
        } catch (PersistenceException e) {
            this.factory = Persistence.createEntityManagerFactory(UNIT_NAME);
            manager = factory.createEntityManager();
        }finally{
            request.setAttribute("EntityManager", manager);
        }
        

        try {
            chain.doFilter(request, response);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    @Override
    public void destroy() {
        this.factory.close();
    }

}
