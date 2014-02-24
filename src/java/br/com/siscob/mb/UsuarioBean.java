/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscob.mb;

import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.neg.CondominioNeg;
import br.com.siscob.neg.UsuarioNeg;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Douglas
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends GenericBean<Usuario>{

    private String filtroSimples;
    private String cpf;
    private String nomeUsuario;
    private List<Condominio> condominios;
    private Condominio condominio;
    private final CondominioNeg condominioNeg;
    
    public UsuarioBean() {
        super(new UsuarioNeg());
        condominioNeg = new CondominioNeg();
    }

    public String getFiltroSimples() {
        return filtroSimples;
    }

    public void setFiltroSimples(String filtroSimples) {
        this.filtroSimples = filtroSimples;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<Condominio> getCondominios() {
        if(condominios == null)
            condominios = condominioNeg.consultar();
        
        return condominios;
    }

    public void setCondominios(List<Condominio> condominios) {
        this.condominios = condominios;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public void filtrarDetalhado(){
        List<Usuario> usuarios = ((UsuarioNeg) super.obterNeg()).consultar(nomeUsuario, cpf, condominio);
        super.setListaObjetos(usuarios);
    }
    
    public void filtrarSimplificado(){
        List<Usuario> usuarios = ((UsuarioNeg) super.obterNeg()).consultar(nomeUsuario, cpf);
        super.setListaObjetos(usuarios);
    }

    @Override
    Usuario iniciarObjeto() {
        return new Usuario();
    }

    @Override
    List<Usuario> carregarLista() {
        return ((UsuarioNeg) super.obterNeg()).consultar();
    }
    
}
