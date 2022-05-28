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
import br.com.bankaccountmanagement.repositories.AccountRepository;
import br.com.bankaccountmanagement.repositories.TransactionRepository;
import br.com.bankaccountmanagement.requestDto.DepositRequestDto;
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
		AccountModel accountModel = Utils.createAccountModel(0.0, 12000.00);
		Optional<AccountModel> oAccocuntModel = Optional.of(accountModel);
		
		TransactionModel result = Utils.createTransactionModel(1L, 2L, 2000.0);
		
		DepositRequestDto depositRequestDto = Utils.createDepositRequestDto(AccountType.CORRENTE, 2000.00);
		
		Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(oAccocuntModel);
		
		Mockito.when(transactionRepositoryMock.save(ArgumentMatchers.any())).thenReturn(result);

		TransactionModel depositAccount = transactionService.depositAccount(depositRequestDto,1L);
		
		Assertions.assertNotNull(depositAccount);
		Assertions.assertEquals(depositRequestDto.getValue(), depositAccount.getValue());
	}

}
