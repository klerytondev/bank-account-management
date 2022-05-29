package br.com.bankaccountmanagement.servicesTest;

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
import br.com.bankaccountmanagement.models.TransactionModel;
import br.com.bankaccountmanagement.models.enums.AccountType;
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.TransactionRepository;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
import br.com.bankaccountmanagement.requestDto.WithdrawRequestDto;
import br.com.bankaccountmanagement.services.TransactionService;
import br.com.bankaccountmanagement.utils.Utils;

@DisplayName("Testes de camadas dos services")
public class TransactionServiceTest {

	@InjectMocks
	TransactionService transactionService;

	@Mock
	private TransactionModel transactionModelMock;

	@Mock
	private TransactionRepository transactionRepositoryMock;

	@Mock
	private AccountRepository accountRepositoryMock;

	@Mock
	private DepositRequestDto depositRequestDtoMock;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Realiza o depósito de dinheiro em uma account")
	void shouldDepositSuccessfully() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(0.0, 12000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Retorno da transação mockada do BD
		TransactionModel result = Utils.createTransactionModel(1L, 2L, 2000.0);
		// Cria o DTO da requisição de deposito
		DepositRequestDto depositRequestDto = Utils.createDepositRequestDto(AccountType.CORRENTE, 2000.00);

		// Ação
		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);
		// Salva a transaction com mock do transactionRepository
		Mockito.when(transactionRepositoryMock.save(ArgumentMatchers.any())).thenReturn(result);
		// Executa o metodo de deposito da tranctionService
		TransactionModel deposit = transactionService.depositAccount(depositRequestDto, 1L);

		// Validações
		// Verifica se a conta esta ativa
		Assertions.assertTrue(accountModel.getActiveFlag().equals(ActiveFlag.ACTIVE));
		// Verifica se a transação de deposito é notnull
		Assertions.assertNotNull(deposit);
		// Verifica se o valor solicitado para deposito é igual ao valor que realmente
		// foi depositado
		Assertions.assertEquals(depositRequestDto.getValue(), deposit.getValue());
	}

	@Test
	@DisplayName("Account não encontrada para deposito")
	void shouldDepositFailAccountNotFound() {
		// Cenário
		// Cria o DTO da requisição de deposito
		DepositRequestDto depositRequestDto = Utils.createDepositRequestDto(AccountType.CORRENTE, 2000.00);

		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(5L)).thenReturn(Optional.empty());

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			transactionService.depositAccount(depositRequestDto, 5L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("Account not found.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Account bloqueada para deposito")
	void shouldDepositFailAccountBlocked() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelBlocked(0.0, 12000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Cria o DTO da requisição de deposito
		DepositRequestDto depositRequestDto = Utils.createDepositRequestDto(AccountType.CORRENTE, 2000.00);

		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			transactionService.depositAccount(depositRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("The account is blocked, it is not possible to make a deposit.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Realiza o saque de dinheiro em uma account")
	void shoulWithdrawSuccessfully() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(1200.00, 12000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Retorno da transação mockada do BD
		TransactionModel result = Utils.createTransactionModel(1L, 2L, 200.0);
		// Cria o DTO da requisição de saque
		WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 200.00);

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
	
	@Test
	@DisplayName("Account não encontrada para saque")
	void shouldWithdrawFailAccountNotFound() {
		// Cenário
		// Cria o DTO da requisição de saque
		WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 200.00);

		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(5L)).thenReturn(Optional.empty());

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			transactionService.withdraw(withdrawRequestDto, 5L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("Account not found.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Account bloqueada para saque")
	void shouldWithdrawFailAccountBlocked() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelBlocked(0.0, 12000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Cria o DTO da requisição de saque
		WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 200.00);

		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);

		// Ação/Validação
		// Executa o metodo de saque da tranctionService e valida a exception lançada
		try {
			transactionService.withdraw(withdrawRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("The account is blocked, it is not possible to make a withdrawal.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Account sem limite para saque")
	void shouldFailAccountWithoutLimit() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(1200.00, 100.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Cria o DTO da requisição de saque
		WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 200.00);

		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);

		//Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			transactionService.withdraw(withdrawRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("Insufficient limit for withdrawal.", e.getMessage());
		}
		
		// Validações
		// Verifica se a conta esta ativa
		Assertions.assertTrue(accountModel.getActiveFlag().equals(ActiveFlag.ACTIVE));
		// Verifica se o valor solicitado para saque é > ao limite
		Assertions.assertTrue(withdrawRequestDto.getValue() > accountModel.getWithdrawalLimit());
	}
	
	@Test
	@DisplayName("Account sem saldo para saque")
	void shouldFailAccountWithoutBalance() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(100.00, 2000.00);
		// Transformo account em optional
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		// Cria o DTO da requisição de saque
		WithdrawRequestDto withdrawRequestDto = Utils.createWithdrawRequestDto(AccountType.CORRENTE, 200.00);

		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);

		//Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			transactionService.withdraw(withdrawRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("Insufficient balance for withdrawal.", e.getMessage());
		}
		
		// Validações
		// Verifica se a conta esta ativa
		Assertions.assertTrue(accountModel.getActiveFlag().equals(ActiveFlag.ACTIVE));
		// Verifica se o valor solicitado para saque é > que saldo
		Assertions.assertTrue(withdrawRequestDto.getValue() > accountModel.getBalance());
	}
}
