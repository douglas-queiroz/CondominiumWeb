/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.neg;

import br.com.siscob.dao.UsuarioDAO;
import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.SegurancaUtil;
import java.io.Serializable;
import java.util.List;

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

    @Override
    public void salvar(Usuario objeto) throws Exception {
        if(objeto.getId() == 0)
            objeto.setPermissao("ROLE_USER");
        
        objeto.setSenha(SegurancaUtil.criptografar(objeto.getSenha()));
        
        super.salvar(objeto);
    }
    
    public List<Usuario> consultar(String nome, String cpf, Condominio condominio){
        return ((UsuarioDAO) super.obterDAO()).consultar(nome, cpf, condominio);
    }
    
    public List<Usuario> consultar(String nome, String cpf){
        return ((UsuarioDAO) super.obterDAO()).consultar(nome, cpf);
    }
    
    public Usuario obter(String login){
        return ((UsuarioDAO) super.obterDAO()).obter(login);
    }
}
