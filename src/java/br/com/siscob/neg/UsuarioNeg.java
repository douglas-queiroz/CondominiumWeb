/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.neg;

import br.com.siscob.dao.UsuarioDAO;
import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.util.Util;
import br.com.tronic.exception.ValidacaoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de implementação do serviço de Usuário
 *
 * @author Douglas Queiroz
 */
public class UsuarioNeg extends GenericNeg<Usuario> implements Serializable {

    private static final long serialVersionUID = -6111899387890527860L;

    public UsuarioNeg() {
        super(new UsuarioDAO());
    }

    @Override
    public void salvar(Usuario objeto) throws Exception {
        this.validar(objeto);

        if (objeto.getId() == 0) {
            objeto.setPermissao("ROLE_USER");
        }

        objeto.setSenha(Util.criptografar(objeto.getSenha()));

        super.salvar(objeto);
    }

    public List<Usuario> consultar(String nome, String cpf, Condominio condominio) {
        return ((UsuarioDAO) super.obterDAO()).consultar(nome, cpf, condominio);
    }

    public List<Usuario> consultar(String filtroDefault) {
        return ((UsuarioDAO) super.obterDAO()).consultar(filtroDefault);
    }

    public Usuario obter(String login) {
        return ((UsuarioDAO) super.obterDAO()).obter(login);
    }

    private void validar(Usuario objeto) throws ValidacaoException {
        List<String> msgs = new ArrayList<String>();

        if (!Util.validaCPF(objeto.getCpf())) {
            msgs.add("CPF Inválido!");
        }

        if (objeto.getNome().equals("")) {
            msgs.add("O campo nome é obrigatório!");
        }

        if (objeto.getSenha().equals("")) {
            msgs.add("O campo senha é obrigatório!");
        }

        Usuario usuario = this.obter(objeto.getCpf());
        if (usuario != null && usuario.getId() != objeto.getId()) {
            msgs.add("Este usuário já está cadastrado!");
        }

        if (!msgs.isEmpty()) {
            throw new ValidacaoException(msgs);
        }
    }

    public List consultarUltimosAcessos() {
        return ((UsuarioDAO) super.obterDAO()).consultarUltimosAcessos();
    }
}
