package br.com.bankaccountmanagement.responseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bankaccountmanagement.models.AccountModel;

public class BalanceResponseDto {
	
	private Long idAccount;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime date;
	private Double balance;
	
	public BalanceResponseDto() {
	}

	public BalanceResponseDto(AccountModel accountModel) {
		this.idAccount = accountModel.getId();
		this.balance = accountModel.getBalance();
		this.date = LocalDateTime.now();
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
