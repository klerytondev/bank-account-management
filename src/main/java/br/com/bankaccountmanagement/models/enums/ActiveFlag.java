package br.com.bankaccountmanagement.models.enums;

import javax.validation.constraints.Pattern;

public enum ActiveFlag {

	MASTERCARD("mastercard"), VISA("visa");

	/*
	 * @regex validar os possiveis tipos de bandeiras. Só são permitidas as bandeiras
	 * MASTERCARD("mastercard"), VISA("visa")
	 * 
	 */
	@Pattern(regexp = "MASTERCARD|VISA")
	private String ativeFlag;

	private ActiveFlag(String ativeFlag) {
		this.ativeFlag = ativeFlag;
	}

	public String getativeFlag() {
		return ativeFlag;
	}

	public void setativeFlag(String ativeFlag) {
		this.ativeFlag = ativeFlag;
	}
}
