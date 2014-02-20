/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.mb;

import br.com.siscob.neg.GenericNeg;
import br.com.siscob.util.FacesUtil;
import java.util.List;
import org.eclipse.persistence.exceptions.ValidationException;
import org.primefaces.context.RequestContext;

/**
 * Classe de controle de view generica.
 *
 * @author Douglas Queiroz
 * @param <T> - Entidade
 */
public abstract class GenericBean<T> {

    /**
     * Entidade principal da view
     */
    private T objeto;
    
    private GenericNeg<T> neg;
    
    /**
     * Lista da entidade principal
     */
    private List<T> listaObjetos;

    /**
     * Construtor
     * @param neg
     */
    public GenericBean(GenericNeg<T> neg) {
        this.neg = neg;
        prepararAdicionar();
    }

    /**
     * Este método é executado no click do Novo "Objeto"
     */
    public void prepararAdicionar() {
        this.objeto = this.iniciarObjeto();
    }
    
    /**
     * Este método é executado no clicl do editar.
     * 
     * @param objeto - Objeto a ser editado
     */
    public void prepararEditar(T objeto) {
        this.objeto = objeto;
    }

    /**
     * Método generico para insert e update
     * 
     */
    @SuppressWarnings("unchecked")
    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        
        try {
            this.obterNeg().salvar(this.getObjeto());
            FacesUtil.exibirMensagemSucesso("Sucesso", "Registro salva com sucesso!");
            context.addCallbackParam("valido", true);
            
            listaObjetos = this.carregarLista();
        } catch (Exception e) {
            context.addCallbackParam("valido", false);
        }
    }

    /**
     * Método delete
     */
    @SuppressWarnings("unchecked")
    public void deletar() {
        try {
            this.obterNeg().excluir(this.getObjeto());
            listaObjetos = this.carregarLista();
        } catch (ValidationException e) {
        }
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public List<T> getListaObjetos() {
        if (listaObjetos == null) {
            listaObjetos = this.carregarLista();
        }

        return listaObjetos;
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * Métedo para instanciar a conta e suas dependências.
     * 
     * @return Conta
     */
    abstract T iniciarObjeto();

    /**
     * Método de consulta para popular o grid.
     * 
     * @return Lista de objetos
     */
    abstract List<T> carregarLista();
    
    protected GenericNeg<T> obterNeg(){
        return neg;
    }
}
