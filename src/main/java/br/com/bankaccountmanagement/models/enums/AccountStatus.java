package br.com.bankaccountmanagement.models.enums;

import javax.validation.constraints.Pattern;

/*
 * Enum utilizado para posssiveis entradas de status da conta
 * */

public enum AccountStatus {

	BLOCK("block"), ACTIVE("active");

	/*
	 * @regex validar os possiveis tipos de bandeiras. Só são permitidas as
	 * bandeiras BLOCK("block"), ACTIVE("active")
	 */
	@Pattern(regexp = "BLOCK|ACTIVE")
	private String accountStatus;

	private AccountStatus(@Pattern(regexp = "BLOCK|ACTIVE") String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
