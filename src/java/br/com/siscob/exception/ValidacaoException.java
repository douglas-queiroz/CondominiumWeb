/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tronic.exception;

import java.util.List;

/**
 *
 * @author Douglas
 */
@SuppressWarnings("serial")
public class ValidacaoException extends Exception {

    private List<String> mensagens;

    public ValidacaoException(List<String> mensagens) {
        super("Erro de validação");
        this.mensagens = mensagens;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
