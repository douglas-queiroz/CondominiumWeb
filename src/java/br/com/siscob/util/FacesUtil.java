package br.com.siscob.util;

import br.com.siscob.model.Usuario;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe utilit�ria para desenvolvimento JSF
 */
public class FacesUtil {

    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static void exibirMensagemSucesso(String titulo, String mensagem) {
        exibirMensagem(FacesMessage.SEVERITY_INFO, titulo, mensagem);
    }

    public static void exibirMensagemAlerta(String titulo, String mensagem) {
        exibirMensagem(FacesMessage.SEVERITY_WARN, titulo, mensagem);
    }

    public static void exibirMensagemErro(String titulo, String mensagem) {
        exibirMensagem(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
    }

    private static void exibirMensagem(FacesMessage.Severity severity, String titulo, String mensagem) {
        FacesMessage facesMessage = new FacesMessage(severity, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    @SuppressWarnings("rawtypes")
    public static Map getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    
    public static Usuario obterUsuarioSessao(){
        return (Usuario) FacesUtil.getServletRequest().
                                    getSession().getAttribute("usuario");
    }
}
