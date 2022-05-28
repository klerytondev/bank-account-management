package br.com.bankaccountmanagement.utils;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;

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

	public static AccountModel createAccountModel(Double balance, Double withdrawLimit) {
		AccountModel accountModel = new AccountModel();
		accountModel.setAccountType(AccountType.CORRENTE);
		accountModel.setActiveFlag(ActiveFlag.MASTERCARD);
		accountModel.setBalance(balance);
		accountModel.setWithdrawalLimit(withdrawLimit);
		return accountModel;
	}
	
}
