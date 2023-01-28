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
import br.com.bankaccountmanagement.models.enums.ActiveFlag;
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.PeopleRepository;
import br.com.bankaccountmanagement.requestDto.AccountActiveFlagRequestDto;
import br.com.bankaccountmanagement.requestDto.AccountRequestDto;
import br.com.bankaccountmanagement.utils.Utils;

/**
 * @author: Kleryton de souza
 */
@DisplayName("Testes da service de Account")
public class AccountServiceTest {

	@InjectMocks
	AccountService accountService;

	@Mock
	private AccountModel accountModel;

	@Mock
	private AccountRepository accountRepositoryMock;

	@Mock
	private PeopleRepository peopleRepositoryMock;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Realiza a criação de uma account")
	void shouldCreateAccount() {
		// Cenário
		// Cria uma pessoa
		PeopleModel peopleModel = Utils.createPeople("kleryton", "07869696447");
		// Crio a account ativa
		AccountModel accountModel = Utils.createAccountModelActive(1200.00, 12000.00);
		// Seta uma pessoa na conta
		accountModel.setPeople(peopleModel);
		// Cria o DTO da requisição de criar account
		AccountRequestDto accountRequestDto = Utils.createAccountRequestDto();

		// Ação
		// Recupera a people com Mock do peopleRepository
		 Mockito.when(peopleRepositoryMock.findById(1L)).thenReturn(Optional.of(peopleModel));
		// Salva a account com mock do accountRepositoryMock
		Mockito.when(accountRepositoryMock.save(ArgumentMatchers.any())).thenReturn(accountModel);
		// Executa o metodo de criar account do accountService
		AccountModel account = accountService.createAccount(accountRequestDto, 1L);

		// Validações
		// Verifica se a account é notnull
		Assertions.assertNotNull(account);
	}

	@Test
	@DisplayName("Error ao criar acount por people não encontrada")
	void shouldFailPeopleNotFound() {
		// Cenário
		/// Cria o DTO da requisição de criar account
		AccountRequestDto accountRequestDto = Utils.createAccountRequestDto();

		// Recupera a people com Mock do peopleRepository
		Mockito.when(peopleRepositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			accountService.createAccount(accountRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("People not found.", e.getMessage());
		}
	}

	@Test
	@DisplayName("Altera o Status da account")
	void shouldChangeStatus() {
		// Cenário
		// Crio a account
		AccountModel accountModel = Utils.createAccountModelActive(1200.00, 12000.00);
		// Cria o DTO da requisição para alterar status de account
		AccountActiveFlagRequestDto accActiveFlagRequestDto = Utils.cretaeAccountActiveRequestDto();
		accountModel.setActiveFlag(ActiveFlag.BLOCK);

		// Ação
		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(Optional.of(accountModel));
		// Salva a account com mock do accountRepositoryMock
		Mockito.when(accountRepositoryMock.save(ArgumentMatchers.any())).thenReturn(accountModel);
		// Executa o metodo de criar account do accountService
		AccountModel account = accountService.activeFlag(accActiveFlagRequestDto, 1L);

		// Validações
		// Verifica se a account é notnull
		Assertions.assertNotNull(account);
		Assertions.assertEquals(accActiveFlagRequestDto.getAccountStatus(), account.getActiveFlag().toString());
	}

	@Test
	@DisplayName("Account não encontrada para alterar status")
	void shouldChangeStatusFailAccountNotFound() {
		// Cenário
		// Cria o DTO da requisição para alterar status de account
		AccountActiveFlagRequestDto accActiveFlagRequestDto = Utils.cretaeAccountActiveRequestDto();

		// Recupera a account com Mock do accountRepository
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(Optional.empty());

		// Ação/Validação
		// Executa o metodo de deposito da tranctionService e valida a exception lançada
		try {
			accountService.activeFlag(accActiveFlagRequestDto, 1L);
			Assertions.fail("Deve lançar uma exception");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.assertEquals("Account not found.", e.getMessage());
		}
	}

}
