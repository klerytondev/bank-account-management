package br.com.bankaccountmanagement.models.enums;

import javax.validation.constraints.Pattern;

/**
 * @author: Kleryton de souza
 *
 * Enum utilizado para posssiveis entradas de status da conta
 * */
public enum ActiveFlag {

	BLOCK("block"), ACTIVE("active");

	/*
	 * @regex validar os possiveis tipos de bandeiras. Só são permitidas as
	 * bandeiras BLOCK("block"), ACTIVE("active")
	 */
	@Pattern(regexp = "BLOCK|ACTIVE")
	private String activeFlag;

	private ActiveFlag(@Pattern(regexp = "BLOCK|ACTIVE") String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
}
