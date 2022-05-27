package br.com.bankaccountmanagement.requestDto;

import javax.validation.constraints.NotEmpty;

import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;

/*
 * Esta classe Transferi e manipula atributos recebidos por parametro da account via controller
 * */
public class AccountReqeustDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor
	@NotEmpty(message = "{campo.balance.obrigatorio}")
	private Double balance;

	@NotEmpty(message = "{campo.limite.obrigatorio}")
	private Double withdrawalLimit;

	private ActiveFlag activeFlag;

	private AccountType accountType;

	public AccountReqeustDto() {
	}

	public AccountReqeustDto(@NotEmpty(message = "{campo.balance.obrigatorio}") Double balance,
			@NotEmpty(message = "{campo.limite.obrigatorio}") Double withdrawalLimit, ActiveFlag activeFlag,
			AccountType accountType) {
		this.balance = balance;
		this.withdrawalLimit = withdrawalLimit;
		this.activeFlag = activeFlag;
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(Double withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}

	public ActiveFlag getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(ActiveFlag activeFlag) {
		this.activeFlag = activeFlag;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

}
