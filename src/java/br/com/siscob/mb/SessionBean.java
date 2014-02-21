/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.mb;

import br.com.siscob.model.Usuario;
import br.com.siscob.neg.UsuarioNeg;
import br.com.siscob.util.FacesUtil;
import br.com.siscob.util.SegurancaUtil;
import br.com.tronic.exception.ValidacaoException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Classe de controle da sessão do usuário
 *
 * @author Douglas Queiroz
 */
@ManagedBean
@SessionScoped
public class SessionBean extends GenericBean<Usuario> implements Serializable {

    /**
     * UID serial version
     */
    private static final long serialVersionUID = -4824275057863504695L;

    /**
     * Usuário
     */
    private Usuario usuario;

    private String senha;
    private String senhaConfirma;

    public SessionBean() {
        super(new UsuarioNeg());
    }

    /**
     * Este método pega o login do usuário e consulta no banco.
     */
    private void carregarUsuario() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuario = ((UsuarioNeg) super.obterNeg())
                        .obter(((User) authentication.getPrincipal()).getUsername());
                FacesUtil.getServletRequest().getSession().setAttribute("usuario", usuario);
            }
        }
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            this.carregarUsuario();
        }

        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirma() {
        return senhaConfirma;
    }

    public void setSenhaConfirma(String senhaConfirma) {
        this.senhaConfirma = senhaConfirma;
    }

    @Override
    Usuario iniciarObjeto() {
        return null;
    }

    @Override
    List<Usuario> carregarLista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void alterarSenha() {

        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (!senha.equals(senhaConfirma)) {
                FacesUtil.exibirMensagemAlerta("Atenção",
                        "As senha não estão iguais!");
                throw new ValidacaoException("Usuário Iválido");
            }

            senha = SegurancaUtil.criptografar(senha);
            
            usuario.setSenha(senha);
            ((UsuarioNeg) super.obterNeg()).salvar(usuario);
            context.addCallbackParam("valido", true);

            senha = "";
            senhaConfirma = "";

            FacesUtil.exibirMensagemSucesso("Sucesso", "Senha alterada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            context.addCallbackParam("valido", false);
        }
    }
}
