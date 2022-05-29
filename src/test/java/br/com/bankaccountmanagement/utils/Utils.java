package br.com.bankaccountmanagement.utils;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;

public class Utils {

	public static AccountType createType(String type) {
		return AccountType.valueOf(type);
	}

	public static ActiveFlag createActiveFlag(String flag) {
		return ActiveFlag.valueOf(flag);
	}

	public static TransactionModel createTransactionModel(Long idAccount, Double value) {
		return new TransactionModel(idAccount, value);
	}
	
	public static TransactionModel createTransactionModel(Long idAccount, Long idTransaction, Double value) {
		return new TransactionModel(idAccount, idTransaction, value);
	}

	public static DepositRequestDto createDepositRequestDto(AccountType accountType, Double Value) {
		DepositRequestDto depositRequestDto = new DepositRequestDto();
		depositRequestDto.setAccountType(accountType);
		depositRequestDto.setValue(Value);
		return depositRequestDto;
	}

	public static AccountModel createAccountModelActive(Double balance, Double withdrawLimit) {
		AccountModel accountModel = new AccountModel();
		accountModel.setIdAccount(1L);
		accountModel.setAccountType(AccountType.CORRENTE);
		accountModel.setActiveFlag(ActiveFlag.ACTIVE);
		accountModel.setBalance(balance);
		accountModel.setWithdrawalLimit(withdrawLimit);
		return accountModel;
	}
	
	public static AccountModel createAccountModelBlocked(Double balance, Double withdrawLimit) {
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountType(AccountType.CORRENTE);
		accountModel.setActiveFlag(ActiveFlag.BLOCK);
		accountModel.setBalance(balance);
		accountModel.setWithdrawalLimit(withdrawLimit);
		return accountModel;
	}
	
	public static WithdrawRequestDto createWithdrawRequestDto(AccountType accountType, Double Value) {
		WithdrawRequestDto withdrawRequestDto = new WithdrawRequestDto();
		withdrawRequestDto.setAccountType(accountType);
		withdrawRequestDto.setValue(Value);
		return withdrawRequestDto;
	}
	
}
