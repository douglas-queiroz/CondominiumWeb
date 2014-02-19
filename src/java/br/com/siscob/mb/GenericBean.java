/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.mb;

import br.com.siscob.neg.GenericNeg;
import java.util.List;
import org.eclipse.persistence.exceptions.ValidationException;

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
    }

    /**
     * Este método é executado no click do Novo "Objeto"
     * 
     * @return nome da página
     */
    public String prepararAdicionar() {
        this.objeto = this.iniciarObjeto();
        
        return "cadastro";
    }
    
    /**
     * Este método é executado no clicl do editar.
     * 
     * @param objeto - Objeto a ser editado
     * @return nome da página
     */
    public String prepararEditar(T objeto) {
        this.objeto = objeto;
        
        return "cadastro";
    }

    /**
     * Método generico para insert e update
     * 
     * @return nome da pagina seguinte
     */
    @SuppressWarnings("unchecked")
    public String salvar() {

        try {
            this.obterNeg().salvar(this.getObjeto());
            listaObjetos = this.carregarLista();
            
            return "index";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            
            return null;
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
