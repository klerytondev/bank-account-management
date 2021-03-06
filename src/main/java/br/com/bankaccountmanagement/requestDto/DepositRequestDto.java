package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bankaccountmanagement.models.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Kleryton de souza
 * 
 * Este DTO recebe os parametros passados via Json para realizar um deposito em uma account
 * */
public class DepositRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "Tipo da account. CORRENTE|POUPANCA ")
	@NotNull(message = "{campo.type.obrigatorio}")
	private AccountType accountType;

	@Schema(description = "Valor do deposito. ")
	@Positive(message = "O valor deve ser maior que zero")
	@NotNull(message = "{campo.valordeposito.obrigatorio}")
	private Double value;

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DepositRequestDto [accountType=" + accountType + ", value=" + value + "]";
	}

}
