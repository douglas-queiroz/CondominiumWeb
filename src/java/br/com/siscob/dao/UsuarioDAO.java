/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.dao;

import br.com.siscob.model.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe DAO de usuario
 *
 * @author Douglas Queiroz
 */
public class UsuarioDAO extends GenericDAO<Usuario> implements Serializable{    
    
    /**
     * UID serial version
     */
    private static final long serialVersionUID = 415437696759735269L;

    /**
     * Contrutor
     */
    public UsuarioDAO() {
        super(Usuario.class);
    }

    /**
     * Método para obter um unico usuário pelo login
     * @param login - Login
     * @return Usuario
     */
    public Usuario obter(String login) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cpf", login);
        return super.findOneResult("Usuario.findByCpf", param);
    }
    
}
