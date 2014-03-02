/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.mb;

import br.com.siscob.model.Boleto;
import br.com.siscob.model.Condominio;
import br.com.siscob.model.Usuario;
import br.com.siscob.neg.BoletoNeg;
import br.com.siscob.util.FacesUtil;
import java.io.File;
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
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author Douglas
 */
@ManagedBean
@ViewScoped
public class DashBoardBean implements Serializable {

    private static final long serialVersionUID = 8308275613511523141L;
    private final BoletoNeg boletoNeg = new BoletoNeg();
    private List<Boleto> boletos = new ArrayList<Boleto>();
    private Boleto boleto;
    private Usuario usuario;

    public DashBoardBean() {
        usuario = FacesUtil.obterUsuarioSessao();
        if (usuario.getPermissao().equals("ROLE_USER")) {
            boletos = boletoNeg.consultar(usuario);
        }
    }

    public BoletoViewer gerarBoleto() {
        Condominio condominio = usuario.getCondominio();

        Cedente cedente = new Cedente(condominio.getNome(), condominio.getCnpj());

        Sacado sacado = new Sacado(usuario.getNome(), usuario.getCpf());

        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(condominio.getEndereco().getUf());
        enderecoSac.setLocalidade(condominio.getEndereco().getCidade());
        enderecoSac.setCep(new CEP(condominio.getEndereco().getCep()));
        enderecoSac.setBairro(condominio.getEndereco().getBairro());
        enderecoSac.setLogradouro(condominio.getEndereco().getLogradouro());
        enderecoSac.setNumero(condominio.getEndereco().getNumero());
        sacado.addEndereco(enderecoSac);

        ContaBancaria contaBancaria = new ContaBancaria(boleto.getContaId().getBanco().create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(boleto.getContaId().getConta(), boleto.getContaId().getDigitoCc()));
        contaBancaria.setCarteira(new Carteira(boleto.getContaId().getCarteira()));
        contaBancaria.setAgencia(new Agencia(boleto.getContaId().getAgencia(), boleto.getContaId().getDigitoAg()));

        Titulo tituloBopepo = new org.jrimum.domkee.financeiro.banco.febraban.Titulo(
                contaBancaria, sacado, cedente);
        tituloBopepo.setNossoNumero(boleto.getNossoNumero());
        tituloBopepo.setValor(new BigDecimal(boleto.getValor()));
        tituloBopepo.setDataDoDocumento(boleto.getDataDocumento());
        tituloBopepo.setDataDoVencimento(boleto.getDataVencimento());
        tituloBopepo.setTipoDeDocumento(boleto.getTipoTitulo());
        tituloBopepo.setAceite(boleto.getAceite());
        tituloBopepo.setDesconto(new BigDecimal(boleto.getDesconto()));
        tituloBopepo.setDeducao(new BigDecimal(boleto.getDeducao()));
        tituloBopepo.setMora(new BigDecimal(boleto.getMora()));
        tituloBopepo.setAcrecimo(new BigDecimal(boleto.getAcrescimo()));
        tituloBopepo.setValorCobrado(new BigDecimal(boleto.getValorCobrado()));

        org.jrimum.bopepo.Boleto boletoBopero = new org.jrimum.bopepo.Boleto(tituloBopepo);
        boletoBopero.setLocalPagamento("PREFERENCIALMENTE NAS CASAS LOTÉRICAS ATÉ O VALOR LIMITE");
        boletoBopero.setInstrucaoAoSacado("");

        boletoBopero.setInstrucao1("DESCONTO DE R$	100,00 ATE: 10/06/2013");
        boletoBopero.setInstrucao2("MULTA    DE R$	  8,86 APOS: 10/06/2013");
        boletoBopero.setInstrucao3("JUROS    DE R$	  0,13 AO DIA");
        boletoBopero.setInstrucao4("NÃO RECEBER APOS 30 DIAS DO VENCIMENTO");
        boletoBopero.setInstrucao5("TAXA CONDOMINIAL JUNHO-13 R$ 380,00");
        boletoBopero.setInstrucao6("RAT CONT DE AUTOMACAO PAR 2-3 R$ 23,00");
        boletoBopero.addTextosExtras("txtRsServico", "Serviço personalizado!!! \t teste \n"
                + "Testando o pulo do gato!!!sssssssssssssssssssssssssssssss");

        return new BoletoViewer(boletoBopero);
    }

    public String download() {
        try {
            BoletoViewer boletoViewer = gerarBoleto();

            byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();

            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=boleto.pdf");

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

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }
}
