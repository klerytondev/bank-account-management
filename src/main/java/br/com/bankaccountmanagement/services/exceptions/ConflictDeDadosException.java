package br.com.bankaccountmanagement.services.exceptions;

/**
 * @author: Kleryton de souza
 * 
 * Trata Status 409, caso a solicitação não possa ser concluída devido a um 
 * conflito com o estado atual do recurso de destino
 */
public class ConflictDeDadosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflictDeDadosException(String msg) {
		super(msg);
	}

	public ConflictDeDadosException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
