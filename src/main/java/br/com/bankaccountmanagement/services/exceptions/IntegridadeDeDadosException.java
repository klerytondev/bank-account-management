package br.com.bankaccountmanagement.services.exceptions;

/*
 *  Trata Status 400, caso algum atributo não seja passado no corpo
 */
public class IntegridadeDeDadosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntegridadeDeDadosException(String mensagem) {
		super(mensagem);
	}

	public IntegridadeDeDadosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
