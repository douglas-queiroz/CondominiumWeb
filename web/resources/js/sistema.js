/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function ehValido(xhr, status, args) {
	if (args.validationFailed || !args.valido) {
		jQuery('#dialogCadastro').effect("shake", {
			times : 3
		}, 100);
	} else {
		cadastroDlg.hide();
		jQuery('#loginLink').fadeOut();
	}
}

function boletoValido(xhr, status, args) {
	if (args.validationFailed || !args.valido) {
		jQuery('#dialogCadastro').effect("shake", {
			times : 3
		}, 100);
	} else {
		cadastroDlg.hide();
                boletosGeradosDlg.show();
		jQuery('#loginLink').fadeOut();
	}
}

function boletosGeradosValido(xhr, status, args) {
	if (args.validationFailed || !args.valido) {
		jQuery('#dialogBoletosGerados').effect("shake", {
			times : 3
		}, 100);
	} else {
		boletosGeradosDlg.hide();
		jQuery('#loginLink').fadeOut();
	}
}

function ehItemValido(xhr, status, args) {
	if (args.validationFailed || !args.valido) {
		jQuery('#dialogCadastroItem').effect("shake", {
			times : 3
		}, 100);
	} else {
		cadastroItemDlg.hide();
		if(args.revisao)
			confirmRevisaoDlg.show();
		
		jQuery('#loginLink').fadeOut();
	}
}

function letraMauscula(cmp) {
	$(cmp).val($(cmp).val().toUpperCase());
};

function abrir(URL) {
	var width = 800;
	var height = 650;
	var left = 99;
	var top = 10;
	window
			.open(
					URL,
					"Siscof - Tronic Tecnologia",
					'width='
							+ width
							+ ', height='
							+ height
							+ ', top='
							+ top
							+ ', left='
							+ left
							+ ', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
}

function ehValidoSenha(xhr, status, args) {
	if (args.validationFailed || !args.valido) {
		jQuery('#dialogSenha').effect("shake", {
			times : 3
		}, 100);
	} else {
		cadastroSenhaDlg.hide();
		jQuery('#loginLink').fadeOut();
	}
}

$(document).ready(function() {
	// Carregando ToolTip
	$('img').tooltip();

	// Mascara de Moeda
	$(".maskMoney").maskMoney({
		symbol : '',
		thousands : '.',
		decimal : ',',
		symbolStay : true
	});
});