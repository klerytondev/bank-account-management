package br.com.bankaccountmanagement.models.enums;

import javax.validation.constraints.Pattern;

public enum AccountType {
	
	COREENTE("corrente"), POUPANCA("poupanca");
	
	/*
	 * @regex validar os possiveis tipos de bandeiras. Só são permitidas as bandeiras
	 * COREENTE("corrente"), POUPANCA("poupanca")
	 * 
	 */
	@Pattern(regexp = "CORRENTE|POUPANCA")
	private String accountType;
	
	private AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
