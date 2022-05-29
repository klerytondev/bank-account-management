package br.com.bankaccountmanagement.requestDto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bankaccountmanagement.models.enums.AccountType;
import io.swagger.annotations.ApiModelProperty;

/*
 * Esta classe Transferi e manipula atributos recebidos por parametro da account via controller
 * */
public class AccountRequestDto {

	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor

	@ApiModelProperty(notes = "Limite de saque da conta. ")
	@NotNull(message = "{campo.limite.obrigatorio}")
	private Double withdrawalLimit;

	@ApiModelProperty(notes = "Tipo da conta. CORRENTE ou POUPANCA ")
	private AccountType accountType;

	public AccountRequestDto() {
	}

	public AccountRequestDto(@NotEmpty(message = "{campo.limite.obrigatorio}") Double withdrawalLimit, AccountType accountType) {
		this.withdrawalLimit = withdrawalLimit;
		this.accountType = accountType;
	}

	public Double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(Double withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, withdrawalLimit);
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
		return accountType == other.accountType && Objects.equals(withdrawalLimit, other.withdrawalLimit);
	}

}
