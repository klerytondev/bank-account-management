package br.com.bankaccountmanagement.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.requestDto.AccountActiveFlagRequestDto;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.ExtractByPeriodRequestDto;
import br.com.bankaccountmanagement.requestDto.PeopleRequestDto;
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

	public static List<TransactionModel> createListTransactionModel(Long idAccount) {
		TransactionModel transaction1 = createTransactionModel(idAccount, 1L, 100.0);
		TransactionModel transaction2 = createTransactionModel(idAccount, 2L, 200.0);
		List<TransactionModel> transactionModelsList = new ArrayList<>();
		transactionModelsList.add(transaction1);
		transactionModelsList.add(transaction2);
		return transactionModelsList;
	}

	public static ExtractByPeriodRequestDto createExtractByPeriodRequestDto() {
		LocalDate dateInit = LocalDate.now();
		LocalDate dateFinal = LocalDate.now().plusDays(5);

		ExtractByPeriodRequestDto extractByPeriodRequestDto = new ExtractByPeriodRequestDto(
				DateUtils.convertLocalDateToString(dateInit), DateUtils.convertLocalDateToString(dateFinal));
		return extractByPeriodRequestDto;
	}

	public static PeopleModel createPeople(String name, String cpf) {
		return new PeopleModel(1L, name, cpf, LocalDate.now());
	}

	public static PeopleRequestDto createPeopleRequestDto(String name, String cpf) {
		return new PeopleRequestDto(name, cpf, DateUtils.convertLocalDateToString(LocalDate.now()));
	}

	public static AccountRequestDto createAccountRequestDto() {
		AccountRequestDto accountRequestDto = new AccountRequestDto();
		accountRequestDto.setAccountType(AccountType.CORRENTE);
		accountRequestDto.setWithdrawalLimit(1000.00);
		return accountRequestDto;
	}

	public static AccountActiveFlagRequestDto cretaeAccountActiveRequestDto() {
		AccountActiveFlagRequestDto accountActiveFlagRequestDto = new AccountActiveFlagRequestDto();
		accountActiveFlagRequestDto.setAccountStatus("BLOCK");
		return accountActiveFlagRequestDto;

	}

}
