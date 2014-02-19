/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.UsuarioDAO;
import br.com.siscob.model.Usuario;
import java.io.Serializable;

/**
 * Classe de implementação do serviço de Usuário
 *
 * @author Douglas Queiroz
 */
public class UsuarioNeg extends GenericNeg<Usuario> implements Serializable{
    private static final long serialVersionUID = 2111430079214063708L;

    public UsuarioNeg() {
        super(new UsuarioDAO());
    }    
    
    public Usuario obter(String login){
        return ((UsuarioDAO) super.obterDAO()).obter(login);
    }
}
