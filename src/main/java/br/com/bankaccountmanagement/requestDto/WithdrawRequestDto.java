package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.bankaccountmanagement.models.enums.AccountType;
import io.swagger.annotations.ApiModelProperty;

public class WithdrawRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Tipo da conta. ")
	private AccountType accountType;

	@ApiModelProperty(notes = "Valor que será sacado da account. ")
	// TODO ajustar messagem
	@PositiveOrZero(message = "O valor deve ser maior ou igual a zero")
	// TODO ajustar messagem
	@NotNull(message = "{campo.valorsaque.obrigatorio}")
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
}
