package br.com.siscob.mb;

import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.neg.BoletoNeg;
import br.com.siscob.neg.UsuarioNeg;
import br.com.siscob.util.FacesUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

@ManagedBean
@ViewScoped
public class DashBoardBean
        implements Serializable {

    public DashBoardBean() {
        boletos = new ArrayList();
        usuario = FacesUtil.obterUsuarioSessao();
        if (usuario.getPermissao().equals("ROLE_USER")) {
            boletos = boletoNeg.consultar(usuario);
        } else {
            ultimosAcessos = usuarioNeg.consultarUltimosAcessos();
        }
    }

    public BoletoViewer gerarBoleto() {
        Condominio condominio = usuario.getCondominio();
        Cedente cedente = new Cedente(condominio.getNome(), condominio.getCnpj());
        Sacado sacado = new Sacado(usuario.getNome(), usuario.getCpf());
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(condominio.getEndereco().getUf());
        enderecoSac.setLocalidade(condominio.getEndereco().getCidade());
        enderecoSac.setCep(new CEP(condominio.getEndereco().getCep()));
        enderecoSac.setBairro(condominio.getEndereco().getBairro());
        enderecoSac.setLogradouro(condominio.getEndereco().getLogradouro());
        enderecoSac.setNumero(condominio.getEndereco().getNumero());
        sacado.addEndereco(enderecoSac);
        ContaBancaria contaBancaria = new ContaBancaria(boleto.getContaId().getBanco().create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(boleto.getContaId().getConta()), boleto.getContaId().getDigitoCc()));
        contaBancaria.setCarteira(new Carteira(Integer.valueOf(boleto.getContaId().getCarteira())));
        
        Agencia agencia;
        if(boleto.getContaId().getDigitoAg().isEmpty()){
            agencia = new Agencia(Integer.valueOf(boleto.getContaId().getAgencia()));
        }else{
            agencia = new Agencia(boleto.getContaId().getAgencia(), boleto.getContaId().getDigitoAg());
        }
        contaBancaria.setAgencia(agencia);
        Titulo tituloBopepo = new Titulo(contaBancaria, sacado, cedente);
        tituloBopepo.setNossoNumero(boleto.getNossoNumero());
        tituloBopepo.setValor(BigDecimal.valueOf(boleto.getValor()));
        tituloBopepo.setDataDoDocumento(boleto.getDataDocumento());
        tituloBopepo.setDataDoVencimento(boleto.getDataVencimento());
        tituloBopepo.setTipoDeDocumento(boleto.getTipoTitulo());
        tituloBopepo.setAceite(boleto.getAceite());
        tituloBopepo.setDesconto(BigDecimal.valueOf(boleto.getDesconto()));
        tituloBopepo.setDeducao(BigDecimal.valueOf(boleto.getDeducao()));
        tituloBopepo.setMora(BigDecimal.valueOf(boleto.getMora()));
        tituloBopepo.setAcrecimo(BigDecimal.valueOf(boleto.getAcrescimo()));
        tituloBopepo.setValorCobrado(BigDecimal.valueOf(boleto.getValorCobrado()));
        if (boleto.getContaId().getBanco() == BancosSuportados.CAIXA_ECONOMICA_FEDERAL) {
            tituloBopepo.setParametrosBancarios(new ParametrosBancariosMap("CodigoOperacao", boleto.getContaId().getCodigoOperacao()));
        }
        Boleto boletoBopero = new Boleto(tituloBopepo);
        boletoBopero.setLocalPagamento(boleto.getLocalPagamento());
        boletoBopero.setInstrucao1(boleto.getInstrucaoPagamento());
        return new BoletoViewer(boletoBopero);
    }

    public String download() {
        if (boleto == null) {
            FacesUtil.exibirMensagemAlerta("Atenção", "Selecione um boleto!");
            return null;
        }
        
        try {
            BoletoViewer boletoViewer = gerarBoleto();
            byte pdfAsBytes[] = boletoViewer.getPdfAsByteArray();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=boleto.pdf");
            OutputStream output = response.getOutputStream();
            output.write(pdfAsBytes);
            response.flushBuffer();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesUtil.exibirMensagemErro("Erro", ex.getMessage());
        }
        return null;
    }

    public List getBoletos() {
        return boletos;
    }

    public void setBoletos(List boletos) {
        this.boletos = boletos;
    }

    public br.com.siscob.model.Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(br.com.siscob.model.Boleto boleto) {
        this.boleto = boleto;
    }

    public List getUltimosAcessos() {
        return ultimosAcessos;
    }

    public void setUltimosAcessos(List ultimosAcessos) {
        this.ultimosAcessos = ultimosAcessos;
    }

    private static final long serialVersionUID = 0x734ceca6bf651b45L;
    private final BoletoNeg boletoNeg = new BoletoNeg();
    private final UsuarioNeg usuarioNeg = new UsuarioNeg();
    private List boletos;
    private br.com.siscob.model.Boleto boleto;
    private Usuario usuario;
    private List ultimosAcessos;
}
