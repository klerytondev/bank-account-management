package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;

/*
 * Esta classe Transferi e manipula atributos recebidos por parametro da account via controller
 * */
public class AccountRequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor

	@NotEmpty(message = "{campo.limite.obrigatorio}")
	private Double withdrawalLimit;

	private ActiveFlag activeFlag;

	private AccountType accountType;

	public AccountRequestDto() {
	}

	public AccountRequestDto(@NotEmpty(message = "{campo.limite.obrigatorio}") Double withdrawalLimit,
			ActiveFlag activeFlag, AccountType accountType) {
		this.withdrawalLimit = withdrawalLimit;
		this.activeFlag = activeFlag;
		this.accountType = accountType;
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

	@Override
	public int hashCode() {
		return Objects.hash(accountType, activeFlag, withdrawalLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountRequestDto other = (AccountRequestDto) obj;
		return accountType == other.accountType && activeFlag == other.activeFlag
				&& Objects.equals(withdrawalLimit, other.withdrawalLimit);
	}

}
