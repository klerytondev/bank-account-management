package br.com.bankaccountmanagement.requestDto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bankaccountmanagement.models.enums.AccountType;
import io.swagger.annotations.ApiModelProperty;

public class DepositRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Id da pessoa que a conta pertence. ")
	// TODO ajustar messagem
	@NotNull(message = "Preenchimento obrigatório")
	private Long idPeople;

	@ApiModelProperty(notes = "Id da pessoa que a conta pertence. ")
	// TODO ajustar messagem
	@NotNull(message = "Preenchimento obrigatório")
	private AccountType accountType;

	@ApiModelProperty(notes = "Valor da transferência. ")
	// TODO ajustar messagem
	@Positive(message = "O valor deve ser maior que zero")
	// TODO ajustar messagem
	@NotNull(message = "Preenchimento obrigatório")
	private Double value;

	public Long getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(Long idPeople) {
		this.idPeople = idPeople;
	}

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
