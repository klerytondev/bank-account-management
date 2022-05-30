package br.com.bankaccountmanagement.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bankaccountmanagement.models.AccountModel;
import br.com.bankaccountmanagement.models.PeopleModel;
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.PeopleRepository;
import br.com.bankaccountmanagement.requestDto.PeopleRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.utils.Utils;

@DisplayName("Testes da service de Account")
public class AccountServiceTest {

	
	@InjectMocks
	AccountService accountService;

	@Mock
	private AccountModel accountModel;

	@Mock
	private AccountRepository accountRepositoryMock;
	
	@Mock
	private PeopleRepository peopleRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("Realiza a criação de uma account")
	void shouldCreateAccount() {
		// Cenário
		//Cria uma pessoa
		PeopleModel peopleModel = Utils.createPeople("kleryton", "07869696447");
		
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(1200.00, 12000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		peopleModel.setAccountModels(accountModel);
		// Cria o DTO da requisição de criar account
		PeopleRequestDto peopleRequestDto = Utils.createPeopleRequestDto("kleryton", "07869696447");

		// Ação
		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);
		// Salva a transaction com mock do transactionRepository
		Mockito.when(transactionRepositoryMock.save(ArgumentMatchers.any())).thenReturn(result);
		// Executa o metodo de saque da tranctionService
		TransactionModel withdraw = transactionService.withdraw(withdrawRequestDto, 1L);

		// Validações
		// Verifica se a conta esta ativa
		Assertions.assertTrue(accountModel.getActiveFlag().equals(ActiveFlag.ACTIVE));
		// Verifica se a transação de saque é notnull
		Assertions.assertNotNull(withdraw);
		// Verifica se o valor solicitado para saque é igual ao valor que realmente foi
		// sacado
		Assertions.assertEquals(withdrawRequestDto.getValue(), withdraw.getValue());
		// Verifica se o valor solicitado para saque é <= ao saldo
		Assertions.assertTrue(withdrawRequestDto.getValue() <= accountModel.getBalance());
		// Verifica se o valor solicitado para saque é <= ao limite
		Assertions.assertTrue(withdrawRequestDto.getValue() <= accountModel.getWithdrawalLimit());
	}
	
	
}
