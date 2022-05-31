package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bankaccountmanagement.models.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros de AccountModel passados via Json para criar uma nova account
 * */
public class AccountRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	// Beans Validation para validação de entrada dos objetos
	// Propriedades baseadas em chave=valor
	@Schema(description = "Limite de saque da conta. ")
	@NotNull(message = "{campo.limite.obrigatorio}")
	private Double withdrawalLimit;

	@Schema(description = "Tipo da conta. CORRENTE ou POUPANCA ")
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
