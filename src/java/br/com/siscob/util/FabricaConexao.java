/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.util;

import javax.persistence.EntityManager;

/**
 *
 * @author dqsl
 */
public class FabricaConexao {

    public static EntityManager obterManager() {
        return (EntityManager) FacesUtil.getServletRequest().getAttribute("EntityManager");
    }
}
